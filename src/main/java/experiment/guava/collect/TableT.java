package experiment.guava.collect;

import com.google.common.collect.HashBasedTable;

import java.util.Map;

/**
 * @author : liulei
 **/
public class TableT {
    public static void main(String[] args) {
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1,1,"tom");
        table.put(1,2,"jerry");
        table.put(1,3,"jack");
        boolean contains11 = table.contains(1, 1);
        boolean containsColumn2 = table.containsColumn(2);
        boolean containsRow1 = table.containsRow(1);
        boolean containsValue = table.containsValue("tom");
        String remove = table.remove(1, 3);
        //不存在的元素会返回null
        String s = table.get(3, 4);

        Map<Integer, String> row = table.row(1);
        Map<Integer, String> column = table.column(1);
    }
}
