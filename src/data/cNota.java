package data;

import java.util.ArrayList;
import java.util.Scanner;
import strukturdata.linkedlistproduk;

public class cNota {
    private ArrayList<cProduk> produkDibeli = new ArrayList<>();
    private ArrayList<Integer> jumlahBeli = new ArrayList<>();
    private int bayar = 0;
    private String idPembeli = "";

    private boolean ismember;

    public ArrayList<cProduk> getProdukDibeli() {
        return produkDibeli;
    }

    /**
     * Mengembalikan daftar jumlah untuk setiap produk yang dibeli.
     * 
     * @return ArrayList dari Integer
     */
    public ArrayList<Integer> getJumlahBeli() {
        return jumlahBeli;
    }

    private accountmanager accountManager;

    public void setAccountManager(accountmanager manager) {
        this.accountManager = manager;
    }

    public boolean isMember() {
        return ismember;
    }

    // Tambahan constructor untuk AccountManager
    public cNota(accountmanager accountManager) {
        this.accountManager = accountManager;
    }

    public void tambahProduk(cProduk produk, int jumlah) {
        this.produkDibeli.add(produk);
        this.jumlahBeli.add(jumlah);
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public void setidpembeli(String idPembeli) {
        this.idPembeli = idPembeli;
        if (accountManager != null) {
            this.ismember = accountManager.isMember(idPembeli);

        } else {
            this.ismember = false;
        }

    }

    //batal transaksi
    public void kembalikanStokKeDaftarUtama(linkedlistproduk daftarProdukUtama) {
        // Loop sebanyak produk yang ada di keranjang (nota) saat ini
        for (int i = 0; i < this.produkDibeli.size(); i++) {
            cProduk produkDiKeranjang = this.produkDibeli.get(i);
            int jumlahDiKeranjang = this.jumlahBeli.get(i);

            // Cari produk asli di daftar produk utama berdasarkan nama
            cProduk produkAsli = daftarProdukUtama.getProdukByName(produkDiKeranjang.getNama());

            if (produkAsli != null) {
                // Tambahkan kembali stoknya
                produkAsli.setStock(produkAsli.getStock() + jumlahDiKeranjang);
            }
        }
    }

    public void editProduk(Scanner sc) {
        String pilihan;
        do {
            System.out.println("Apakah ada produk yang ingin dihapus? (y/n): ");
            pilihan = sc.next();
            if (pilihan.equalsIgnoreCase("y")) {
                int idx;
                for (idx = 0; idx < this.produkDibeli.size(); ++idx) {
                    System.out.println(idx + 1 + ". " + ((cProduk) this.produkDibeli.get(idx)).getNama() + " x"
                            + this.jumlahBeli.get(idx));
                }

                System.out.print("Pilih produk yang ingin dihapus: ");
                idx = sc.nextInt() - 1;
                if (idx >= 0 && idx < this.produkDibeli.size()) {
                    cProduk produk = (cProduk) this.produkDibeli.get(idx);
                    produk.setStock(produk.getStock() + (Integer) this.jumlahBeli.get(idx));
                    this.produkDibeli.remove(idx);
                    this.jumlahBeli.remove(idx);
                    System.out.println("Produk berhasil dihapus.");
                } else {
                    System.out.println("Indeks tidak valid.");
                }
            }
        } while (pilihan.equalsIgnoreCase("y"));

    }

    public int hitungTotalSebelumDiskon() {
        int total = 0;

        for (int i = 0; i < this.produkDibeli.size(); ++i) {
            total += this.produkDibeli.get(i).getHarga() * (Integer) this.jumlahBeli.get(i);
        }

        return total;
    }

    public void tampilkanRingkasanPembayaran() {
        int grandTotal = this.hitungTotalSebelumDiskon();
        System.out.println("\n=== RINGKASAN PEMBAYARAN ===");
        if (ismember) {
            System.out.println("Member: " + this.idPembeli);
            int diskon = grandTotal * 10 / 100;
            int totalAkhir = grandTotal - diskon;
            System.out.println("Total Sebelum Diskon: " + grandTotal);
            System.out.println("Diskon Member (10%): " + diskon);
            System.out.println("Total Yang Harus Dibayar: " + totalAkhir);
        } else {
            System.out.println("Pembeli: " + this.idPembeli);
            System.out.println("Total Yang Harus Dibayar: " + grandTotal);
        }

    }

    public boolean prosesPembayaran(Scanner sc) {
        this.hitungTotalYangHarusDibayar();

        int pilihan;
        do {
            this.tampilkanRingkasanPembayaran();
            System.out.print("Masukkan jumlah bayar: ");
            this.setBayar(sc.nextInt());
            if (this.isPaymentValid()) {
                this.tampilkanNota();
                return true;
            }

            System.out.println("\nPembayaran tidak mencukupi!");
            System.out.println("1. Lanjutkan pembayaran");
            System.out.println("2. Batalkan transaksi");
            System.out.print("Pilihan: ");
            pilihan = sc.nextInt();
        } while (pilihan != 2);

        this.kembalikanSemuaStok();
        System.out.println("Transaksi dibatalkan.");
        return false;
    }

    public void tampilkanNota() {
        int grandTotal = this.hitungTotalSebelumDiskon();
        System.out.println("\n=========== NOTA PEMBELIAN ===========");
        if (ismember) {
            System.out.println("Member: " + this.idPembeli);
        } else if (!this.idPembeli.isEmpty()) {
            System.out.println("Pembeli: " + this.idPembeli);
        }

        int diskon;
        for (diskon = 0; diskon < this.produkDibeli.size(); ++diskon) {
            cProduk produk = (cProduk) this.produkDibeli.get(diskon);
            int jumlah = this.jumlahBeli.get(diskon);
            int totalHarga = produk.getHarga() * jumlah;
            System.out.println(produk.getNama() + " x" + jumlah + " = " + totalHarga);
        }

        System.out.println("--------------------------------------");
        int var10001;
        if (ismember) {
            diskon = grandTotal * 10 / 100;
            int totalAkhir = grandTotal - diskon;
            System.out.println("Total Sebelum Diskon : " + grandTotal);
            System.out.println("Diskon Member (10%)  : " + diskon);
            System.out.println("Total Setelah Diskon: " + totalAkhir);
            System.out.println("Bayar : " + this.bayar);
            var10001 = this.bayar - totalAkhir;
            System.out.println("Kembalian : " + var10001);
        } else {
            System.out.println("Total                : " + grandTotal);
            System.out.println("Bayar : " + this.bayar);
            var10001 = this.bayar - grandTotal;
            System.out.println("Kembalian : " + var10001);
        }

        System.out.println("======================================\n");
    }

    public int hitungTotalYangHarusDibayar() {
        int total = this.hitungTotalSebelumDiskon();
        if (ismember) {
            total = total * 90 / 100;
        }

        return total;
    }

    public void tampilkanKeranjangSementara() {
        int total = this.hitungTotalSebelumDiskon();
        System.out.println("\n=========== DAFTAR BELANJA SEMENTARA ===========");
        for (int i = 0; i < this.produkDibeli.size(); ++i) {
            cProduk produk = this.produkDibeli.get(i);
            int jumlah = this.jumlahBeli.get(i);
            int totalHarga = produk.getHarga() * jumlah;
            System.out.println(produk.getNama() + " x" + jumlah + " = " + totalHarga);
        }
        System.out.println("--------------------------------------");
        if (ismember) {
            int diskon = total * 10 / 100;
            int totalAkhir = total - diskon;
            System.out.println("Total Sebelum Diskon : " + total);
            System.out.println("Diskon Member (10%)  : " + diskon);
            System.out.println("Total Setelah Diskon: " + totalAkhir);
        } else {
            System.out.println("Total : " + total);
        }
        System.out.println("======================================\n");
    }

    private boolean isPaymentValid() {
        return this.bayar >= this.hitungTotalYangHarusDibayar();
    }

    private void kembalikanSemuaStok() {
        for (int i = 0; i < this.produkDibeli.size(); ++i) {
            cProduk produk = (cProduk) this.produkDibeli.get(i);
            produk.setStock(produk.getStock() + (Integer) this.jumlahBeli.get(i));
        }

    }
}
