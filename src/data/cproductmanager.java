package data;

import java.util.ArrayList;
import java.util.Scanner;

public class cproductmanager {
    Scanner sc = new Scanner(System.in);
    private ArrayList<cProduk> produkList;

    // getter untuk akses produk list
    public ArrayList<cProduk> getproduklist() {
        return produkList;
    }

    public void addproduct(cProduk produk) {
        produkList.add(produk);
        System.out.println("produk berhasil ditambahkan!");
    }

    public void displayproduct() {
        int i = 0;
        for (cProduk produk : produkList) {
            System.out.println(produk);
            i++;
        }
    }

    public void updateproduct(Scanner sc) {
        for (int i = 0; i < produkList.size(); i++) {
            System.out.println((i + 1) + ". " + produkList.get(i).getNama());
        }
        System.out.print("Pilih produk yang ingin diubah: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < produkList.size()) {
            cProduk produk = produkList.get(index);
            System.out.println("Harga Lama: " + produk.getHarga());
            System.out.print("Harga Baru: ");
            produk.setHarga(sc.nextInt());
            System.out.println("Tahun Lama: " + produk.getExpired());
            System.out.print("Tahun Baru: ");
            produk.setExpired(sc.nextInt());
            System.out.println("Stock Lama: " + produk.getStock());
            System.out.print("Stock Baru: ");
            produk.setStock(sc.nextInt());
            System.out.println("Data berhasil diubah.");
        } else
            System.out.println("Pilihan tidak valid");
    }

    public void deleteproduct(Scanner sc) {
        for (int i = 0; i < produkList.size(); i++) {
            System.out.println((i + 1) + ". " + produkList.get(i).getNama());
        }
        System.out.print("Pilih produk yang ingin dihapus: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < produkList.size()) {
            produkList.get(index).hapus();
            produkList.remove(index);
            System.out.println("Produk berhasil dihapus.");
        } else
            System.out.println("Pilihan tidak Valid");
    }

}
