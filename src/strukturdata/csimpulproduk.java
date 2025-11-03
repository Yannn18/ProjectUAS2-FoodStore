package strukturdata;

import data.cProduk;

public class csimpulproduk {
    public cProduk produk;
    public csimpulproduk next;

    public csimpulproduk(cProduk p) {
        produk = p;
        next = null;
    }
}
