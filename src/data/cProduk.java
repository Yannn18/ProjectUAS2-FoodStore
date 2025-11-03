package data;

public class cProduk {
    private String nama;
    private int harga;
    private int expired;
    private int stock;

    // Konstruktor dengan akses public
    public cProduk(String n, int e, int s, int h) {
        nama = n;
        expired = e;
        stock = s;
        harga = h;
        System.out.println("Menu " + nama + " dibuat..");
    }

    // cProduk(String n, int e, int s, int h) {
    // nama = n;
    // harga = h;
    // expired = e;
    // stock = s;
    // System.out.println("Menu " + nama + " dibuat..");
    // }

    // Setter
    public void setNama(String n) {
        nama = n;
    }

    public void setHarga(int h) {
        harga = h;
    }

    public void setExpired(int e) {
        expired = e;
    }

    public void setStock(int s) {
        stock = s;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getExpired() {
        return expired;
    }

    public int getStock() {
        return stock;
    }

    // toString
    @Override
    public String toString() {
        return nama + " [" + harga + "] [" + expired + "] [" + stock + "]";
    }

    public void hapus() {
        System.out.println("Objek " + nama + " dihapus");
        nama = "";
        harga = 0;
        expired = 0;
        stock = 0;
    }
}
