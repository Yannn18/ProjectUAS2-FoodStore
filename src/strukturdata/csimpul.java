package strukturdata;

import data.cNota;
import java.time.LocalDate;

public class csimpul {

    public String idMember; // Bisa diisi nama pembeli atau ID member
    public cNota nota;
    public int status; // 0 = belum diproses, 1 = sudah diproses
    public csimpul next, prev;
    public LocalDate tanggalTransaksi;

    public csimpul(String id, cNota n) {
        idMember = id;
        nota = n;
        status = 0; // Default status saat transaksi dibuat
        this.tanggalTransaksi = LocalDate.now();
        next = prev = null;
    }
}
