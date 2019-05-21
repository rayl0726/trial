package experiment.guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : liulei
 **/
public class MapsT {

    public static List<Book> getBookList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("demon", "11"));
        books.add(new Book("angel", "12"));
        books.add(new Book("monster", "13"));
        return books;
    }

    public static Map<String, Book> normalGetMap(List<Book> books) {
        Map<String, Book> resp = new HashMap<>();
        for(Book temp : books) {
            resp.put(temp.getId(), temp);
        }
        return resp;
    }

    public static void main(String[] args) {
        List<Book> books = getBookList();

        normalGetMap(books);


        Map<String, Book> bookMap = Maps.uniqueIndex(books.iterator(), new Function<Book, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Book input) {
                return input.getId();
            }
        });
        System.out.println(bookMap);
    }
}
