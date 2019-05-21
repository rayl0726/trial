package experiment.concurrent.interrupt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    public final Set<URL> urlsToCrawl = new HashSet<URL>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<URL, Boolean>();
    private static final long TIMEOUT = 5000;
    private static final TimeUnit UNIT = TimeUnit.MICROSECONDS;

    public WebCrawler(URL startUrl){
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start(){
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url: urlsToCrawl)
            submitCrawlTask(url);
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException{
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT)){
                saveUncrawled(exec.getCancelledTasks());
            }

        } finally {
            exec = null;
        }
    }

    /**
     * 提交爬虫任务
     */
    private void submitCrawlTask(URL url) {
        exec.execute(new CrawlTask(url));
    }

    protected abstract List<URL> processPage(URL url);

    /**
     * 保存未完成的
     */
    private void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable task:uncrawled){
            URL url = ((CrawlTask)task).getPage();
            System.out.println("保存未完成的URL："+url);
            urlsToCrawl.add(url);
        }

    }

    //爬虫任务
    private class CrawlTask implements Runnable{
        private final URL url;

        CrawlTask(URL url){
            this.url = url;
        }



        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markUncrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled%n", url);
        }

        @Override
        public void run() {
            int count = 1;
            for (URL link :processPage(url)){
                if(Thread.currentThread().isInterrupted())
                    return;
                System.out.println("[count-" + count + "]提交的爬虫url:"+link);
                count++;
                submitCrawlTask(link);
            }

        }

        public URL getPage(){
            return url;
        }
    }





    public static void main(String[] args) throws MalformedURLException {
        WebCrawler webc = new WebCrawler(new URL("http://site.baidu.com/")) {
            int count = 0;
            @Override
            protected List<URL> processPage(URL url) {
                //获取该url下所有的链接
                //这里省略了该功能
                List<URL> url2 = new ArrayList<URL>();
                try {
                    url2.add(new URL("http://www.cnblogs.com/xingele0917/"+ count));
                    count++;
                    //url2.add(new URL("http://www.zhihu.com/"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                return url2;

            }

        };

        webc.start();
        try {
            Thread.sleep(10);
            webc.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
