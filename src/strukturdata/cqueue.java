package strukturdata;

public class cqueue {
    private csimpul front, rear;
    private static int counter = 0; // Counter untuk nomor urut transaksi

    public cqueue() {
        front = rear = null;
    }

    // Method untuk generate kode transaksi unik (contoh: T001)
    public static String generateKodeTransaksi() {
        counter++;
        return "T" + String.format("%03d", counter);
    }

    public void enqueue(csimpul baru) {
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            baru.prev = rear;
            rear = baru;
        }
        System.out.println("Transaksi " + baru.idMember + " berhasil ditambahkan ke antrian.");
    }

    public csimpul dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return null;
        }
        csimpul temp = front;
        front = front.next;
        if (front == null) {
            rear = null;
        } else {
            front.prev = null;
        }
        temp.next = null;
        System.out.println("Transaksi " + temp.idMember + " diproses.");
        return temp;
    }

    public void view() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return;
        }
        System.out.println("=== DAFTAR ANTRIAN TRANSAKSI ===");
        csimpul current = front;
        int i = 1;
        while (current != null) {
            String statusProses = (current.status == 0) ? "Belum Diproses" : "Sudah Diproses";
            System.out.println(i + ". Pembeli/Member: " + current.idMember + " | Status: " + statusProses);
            current.nota.tampilkanKeranjangSementara();
            current = current.next;
            i++;
        }
        System.out.println("=================================");
    }

    // Getter untuk mendapatkan antrian
    public csimpul getFront() {
        return front;
    }

    // Di dalam file strukturdata/cqueue.java
    // Di dalam file strukturdata/cqueue.java
    public void viewMember(String memberId) {
        if (front == null) {
            System.out.println("Tidak ada pesanan dalam antrian.");
            return;
        }

        System.out.println("Daftar Pesanan untuk " + memberId + ":");
        boolean found = false;
        for (csimpul t = front; t != null; t = t.next) {
            // PERBAIKAN: Menggunakan akses field langsung 't.idMember'
            if (t.idMember.equals(memberId)) {
                System.out.println("-------------------------");
                // Sebaiknya kita panggil juga kode transaksi dari nota
                // System.out.println(" Kode : " + t.getKode()); // getKode() juga tidak ada,
                // kita bisa ambil dari nota jika perlu
                System.out.println("  Status  : " + (t.status == 0 ? "Belum Diproses" : "Sudah Diproses"));
                t.nota.tampilkanKeranjangSementara();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Anda tidak memiliki pesanan dalam antrian.");
        }
        System.out.println("-------------------------");
    }

    // Tambahkan metode ini di dalam file strukturdata/cqueue.java

    public void viewBelumDiproses() {
        if (front == null) {
            System.out.println("Antrian kosong! Semua transaksi sudah diproses.");
            return;
        }
        System.out.println("\n=== DAFTAR TRANSAKSI BELUM DIPROSES ===");
        csimpul current = front;
        int jumlahTransaksi = 0;
        while (current != null) {
            // Kita asumsikan semua yang ada di antrian adalah belum diproses (status 0)
            // Jika Anda punya logika status yang berbeda, tambahkan if(current.status == 0)
            System.out.println("---------------------------------");
            System.out.println("Pembeli/Member: " + current.idMember);
            current.nota.tampilkanKeranjangSementara();
            jumlahTransaksi++;
            current = current.next;
        }
        System.out.println("=================================");
        System.out.println("Total Transaksi Belum Diproses: " + jumlahTransaksi);
        System.out.println("=================================");
    }
}
