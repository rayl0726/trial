package experiment.midware.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));

                            pipeline.addLast(new MyServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(8888).sync();
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
