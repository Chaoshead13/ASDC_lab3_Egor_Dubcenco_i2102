import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
/**
 * <h2>Class Store</h2>
 * <p></p>
 * The Store program implements an application that
 * simply adds data about store.
 *
 * @see Store#toString()
 * @see Store#equals(Object)
 * @see Store#createStoreFromString(String)
 * @see Store#input(File)
 * @author EgorDubcenco
 */
public class Store {

    int Anidentificationnumber;
    String Name;
    String description;
    float UnitPrice;
    int NumberOfUnitsInStock;
    String unit;

    /**
     * <h3>Constructor without parameters.</h3>
     */
    public Store() {
        Anidentificationnumber = 0;
        Name = null;
        description = null;
        UnitPrice = 0;
        NumberOfUnitsInStock = 0;
        unit = null;
    }

    public Store(int Anidentificationnumbe, String Name, String description, float UnitPrice, int NumberOfUnitsInStock, String unit) {
        this.Anidentificationnumber = Anidentificationnumbe;
        this.Name = Name;
        this.description = description;
        this.UnitPrice = UnitPrice;
        this.NumberOfUnitsInStock = NumberOfUnitsInStock;
        this.unit = unit;
    }

    /**
     * <h3>Copy constructor</h3>
     * <p></p>
     *
     * @param stor Object
     */
    public Store(Store stor) {
        this.Anidentificationnumber = stor.Anidentificationnumber;
        this.Name = stor.Name;
        this.description = stor.description;
        this.UnitPrice = stor.UnitPrice;
        this.NumberOfUnitsInStock = stor.NumberOfUnitsInStock;
        this.unit = stor.unit;
    }
    public int getAnidentificationnumber() {

        return Anidentificationnumber;
    }
    /**
     * <h3>Method toString</h3>
     * <p></p>
     *
     * @return Readable string with parameters Store's class.
     */
    @Override
    public String toString() {
        return "Anidentificationnumber='" + Anidentificationnumber + '\'' +
                ", Name='" + Name + '\'' +
                ", description='" + description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", NumberOfUnitsInStock=" + NumberOfUnitsInStock +
                ", unit=" + unit +
                '}';
    }
    public String addToString() {
        return String.join(",",String.valueOf(Anidentificationnumber), Name, description, String.valueOf(UnitPrice), String.valueOf(NumberOfUnitsInStock), unit) + "\n";
    }

    public Store copy(Store stor) {

        return new Store(stor);
    }

    /**
     * <h3>AutoCreated method Equals</h3>
     * <p></p>
     *
     * @param o Object
     * @return True or False.
     */
    //AutoCreated Equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return UnitPrice == store.UnitPrice && NumberOfUnitsInStock == store.NumberOfUnitsInStock && Anidentificationnumber == store.Anidentificationnumber && Name.equals(store.Name) && description.equals(store.description) && unit.equals(store.unit);
    }

    /**
     * <h3>Method createStoreFromString</h3>
     * <p></p>
     * This method reads strings from "goods_in_the_store.csv" and
     * adds parameters to class Store.
     *
     * @param string - String from file
     * @return Object Store.
     */
    public static Store createStoreFromString(String string) {
        String[] stringArray = string.split(",");
        int identificationNumber = Integer.parseInt(stringArray[0]);
        String name = stringArray[1];
        String description = stringArray[2];
        float unitPrice = Float.parseFloat(stringArray[3]);
        int numberOfUnitsInStock = Integer.parseInt(stringArray[4]);
        String unit = stringArray[5];

        return new Store(identificationNumber, name, description, unitPrice, numberOfUnitsInStock, unit);
    }

    public static ArrayList<Store> input(File resource) {
        ArrayList<Store> storeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String st;
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                    // ignore empty lines
                } else {
                    storeList.add(createStoreFromString(st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static void deleteStoreByAnidentificationnumber(File file, int Anidentificationnumber) {
        ArrayList<Store> storeList = input(file);

        for (int i = 0; i < storeList.size(); i++) {
            Store store = storeList.get(i);
            if (store.Anidentificationnumber == Anidentificationnumber) {
                storeList.remove(i);
                break;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Store store : storeList) {
                bw.write(store.Anidentificationnumber + "," + store.Name + "," + store.description + "," + store.UnitPrice + "," + store.NumberOfUnitsInStock + "," + store.unit);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int compareTo(Store other) {
        int cmp = this.Name.compareTo(other.Name);
        if (cmp != 0) {
            return cmp;
        }
        cmp = this.description.compareTo(other.description);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(this.NumberOfUnitsInStock, other.NumberOfUnitsInStock);
    }
}
