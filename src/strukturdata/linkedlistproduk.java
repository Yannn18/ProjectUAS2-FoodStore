package strukturdata;

import data.cProduk;

public class linkedlistproduk {
    private csimpulproduk head;
    private int jnode;

    public linkedlistproduk() {
        head = null;
        jnode = 0;
    }

    public void add(csimpulproduk baru) {
        if (head == null) {
            head = baru;
        } else {
            csimpulproduk temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
        jnode++;
        System.out.println("Produk " + baru.produk.getNama() + " ditambahkan..");
    }

    public void display() {
        System.out.println("=== DAFTAR PRODUK ===");
        int i = 1;
        for (csimpulproduk t = head; t != null; t = t.next) {
            System.out.println(i + ". " + t.produk.getNama() + " - Harga: " + t.produk.getHarga() + " | Stok: "
                    + t.produk.getStock());
            i++;
        }
        System.out.println("=====================");
    }

    public csimpulproduk getHead() {
        return head;
    }

    public int getSize() {
        return jnode;
    }

    public cProduk getProduk(int index) {
        csimpulproduk current = head;
        for (int i = 0; i < index; i++) {
            if (current != null) {
                current = current.next;
            } else {
                return null; // Index out of bounds
            }
        }
        return (current != null) ? current.produk : null;
    }

    public cProduk getProdukByName(String nama) {
        csimpulproduk current = head;
        while (current != null) {
            if (current.produk.getNama().equalsIgnoreCase(nama)) {
                return current.produk; // Produk ditemukan
            }
            current = current.next;
        }
        return null; // Produk tidak ditemukan
    }
}
