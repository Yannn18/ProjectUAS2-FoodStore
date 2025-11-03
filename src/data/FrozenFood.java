package data;

public class FrozenFood {
    private String nama;
    private int harga;
    private int expired;
    private int stock;

   public FrozenFood(String n, int e, int s, int h) {
        nama = n;
        harga = h;
        expired = e;
        stock = s;
        System.out.println(" Menu " + nama + " dibuat..");
    }

    // setter
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

    // getter
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

    // ToString
    public String ToString() {
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
