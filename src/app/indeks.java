package app;

import data.FrozenDrink2;
import data.FrozenFood2;
import data.accountmanager;
import java.util.HashMap;
import java.util.Scanner;
import strukturdata.cqueue;
import strukturdata.csimpulproduk;
import strukturdata.linkedlistproduk;

public class indeks {
    public static linkedlistproduk daftarProduk = new linkedlistproduk();
    public static cqueue antrianTransaksi = new cqueue();
    public static accountmanager account = new accountmanager();
    public static java.util.LinkedList<strukturdata.csimpul> riwayatTransaksi = new java.util.LinkedList<>();
    public static HashMap<String, Integer> totalBelanjaMember = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inisialisasi 5 produk awal menggunakan Linked List
        daftarProduk.add(new csimpulproduk(new FrozenFood2("Nugget", 2026, 100, 20000)));
        daftarProduk.add(new csimpulproduk(new FrozenFood2("Chikuwa", 2026, 100, 10000)));
        daftarProduk.add(new csimpulproduk(new FrozenFood2("Wings", 2026, 100, 20000)));
        daftarProduk.add(new csimpulproduk(new FrozenDrink2("Marjan", 2026, 100, 15000)));
        daftarProduk.add(new csimpulproduk(new FrozenDrink2("Susu UHT", 2026, 100, 5000)));

        account.inisialisasiAkunDefault();
        int pilih = 0;
        do {

            System.out.println("Selamat Datang Di YOIKI Frozen Food");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Login dan Registrasi");
            System.out.println("3. Exit");
            System.out.print("Pilih= ");
            if (sc.hasNextInt()) {
                pilih = sc.nextInt();
                sc.nextLine(); // Clear the newline character from the input buffer
                switch (pilih) {
                    case 1:
                        // ArrayList<cProduk> list = new cproductmanager().getproduklist();
                        // System.out.println("Daftar Produk:");
                        // for (int i = 0; i < list.size(); i++) {
                        // System.out.println((i + 1) + ". " + list.get(i).getNama() + " - Harga:"
                        // + list.get(i).getHarga());
                        // }

                        daftarProduk.display();
                        break;
                    case 2:

                        Registrasi.app(account);
                        // Registrasi.app() handles login and registration
                        break;

                    case 3:

                        System.out.println("Terima Kasih");
                        break;
                    default:
                        System.out.println("input the right choose");
                        break;
                }
            } else {
                System.out.println("harus berupa angka");
                sc.next();
            }
            System.out.println();

        } while (pilih != 3);
        sc.close();
    }
}
