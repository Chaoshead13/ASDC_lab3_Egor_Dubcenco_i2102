import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h2>Class Main</h2>
 * The Main program implements an application that
 * simply displays data from a "goods_in_the_store.csv" file to the standard output.
 *
 * @see Main#linearSearch
 * @see Main#addStore
 * @see Node
 * @see Tree
 * @see Store
 * @author EgorDubcenco
 */
public class Main {
    public static void main(String[] args) {

        File file = new File(".\\src\\goods_in_the_store.csv");
        ArrayList<Store> arrayList = Store.input(file);

        Store.deleteStoreByAnidentificationnumber(new File(".\\src\\goods_in_the_store.csv"),10);
        System.out.printf("Обновленный файл\n\n\n\n\n");
        arrayList = Store.input(file);
        for (Store s : arrayList) {
            System.out.println(s);
        }

        System.out.println("Store was found with Anidentificationnumber = " + linearSearch(arrayList, 20));
        addStore(arrayList, 2, 2, "banana","Yellow Bananas", 30, 50, "kg");
        System.out.println("Store was found with Anidentificationnumber = " + linearSearch(arrayList, 40));

        Tree tree = new Tree();
        for(Store store: arrayList){
            tree.insertNode(store);
        }


    }

    public static int linearSearch(ArrayList<Store> listOfStores, long AnidentificationnumberToBeFound){
        int index = 0;
        for (Store store : listOfStores) {
            if (store.getAnidentificationnumber() == AnidentificationnumberToBeFound) {
                return index;
            } else index++;
        }
        return -1;
    }


    public static void addStore(ArrayList<Store> arrayList, int position, int Anidentificationnumber, String Name, String description, float UnitPrice, int NumberOfUnitsInStock, String unit){
        Store newStore = new Store(Anidentificationnumber, Name, description, UnitPrice, NumberOfUnitsInStock, unit);
        arrayList.add(position, newStore);
        try(FileWriter writer = new FileWriter(".\\src\\goods_in_the_store.csv")) {
            for (int i = 0; i < arrayList.size(); i++) {
                Store store = arrayList.get(i);
                writer.write(store.addToString());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
