package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class accountmanager {
    private ArrayList<UserAccount> userList = new ArrayList<>();

    private LocalDate today = LocalDate.now();
    Scanner sc = new Scanner(System.in);

    private boolean sudahInisialisasi = false; // Flag untuk memastikan inisialisasi hanya sekali

    public void inisialisasiAkunDefault() {
        if (!sudahInisialisasi) {
            // Akun Admin/Karyawan
            userList.add(new UserWithPassword("Geri", "12345", "011"));
            System.out.println("Akun Admin 'Geri' dibuat.");

            // Akun Owner
            userList.add(new owneraccount("Pak Hasan", "qwerty123", "01"));
            System.out.println("Akun Owner 'Pak Hasan' dibuat.");

            sudahInisialisasi = true;
        }
    }

    public static class UserAccount {
        protected String username;
        protected String id;

        public UserAccount(String username, String id) {
            this.username = username;
            this.id = id;
        }

        public void setUsername(String un) {
            username = un;
        }

        public String getUsername() {
            return username;
        }

        public String getId() {
            return id;
        }
        // Tambahkan metode ini di dalam kelas UserAccount.java (atau kelas sejenisnya)

        // public abstract boolean hasPassword();
    }

    public static class Basicmember extends UserAccount {
        private String password;

        public Basicmember(String username, String password, String id) {
            super(username, id);
            this.password = password;
        }

        // public void setUsername(String un) {
        // username = un;
        // }

        // public String getUsername() {
        // return username;
        // }

        public String getpassword() {
            return password;
        }

        public void setpassword(String newPassword) {
            this.password = newPassword;
        }

        public boolean hasPassword() {
            return true;
        }
    }

    public static class UserWithPassword extends UserAccount {
        private String password;

        public UserWithPassword(String username, String password, String id) {
            super(username, id);
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String newPassword) {
            this.password = newPassword;
        }

        public void setid(String id) {
            this.id = id;
        }

        public boolean hasPassword() {
            return true;
        }
    }

    public static class owneraccount extends UserAccount {
        private String password;

        public owneraccount(String username, String password, String id) {
            super(username, id);
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String newPassword) {
            this.password = newPassword;
        }

        public void setid(String id) {
            this.id = id;
        }

        public boolean hasPassword() {
            return true;
        }

    }

    public void addBasicmember(String username, String password) {
        String id = generateId();
        userList.add(new Basicmember(username, password, id));
        System.out.println(" Member berhasil ditambahkan: " + id);
    }

    public void addUserWithPassword(String username, String password) {
        String id = generateId();
        userList.add(new UserWithPassword(username, password, id));
        System.out.println("User dengan Id berhasil ditambahkan: " + id);
    }

    public void addkaryawan(accountmanager.UserWithPassword karyawan) {
        userList.add(karyawan);
        System.out.println("User dengan Id berhasil ditambahkan: " + karyawan.getId());
    }

    public void addowner(accountmanager.owneraccount owner) {
        userList.add(owner);
        System.out.println("User dengan Id berhasil ditambahkan: " + owner.getId());
    }

    public void showAllUsers() {
        System.out.println("=== DAFTAR MEMBER TERDAFTAR ==="); // Judul diubah agar lebih sesuai
        int memberCount = 0;
        for (UserAccount u : userList) {
            // Cek apakah user saat ini adalah instance dari kelas Basicmember
            if (u instanceof Basicmember) {
                System.out.println("ID: " + u.getId() + ", Username: " + u.getUsername());
                memberCount++;
            }
        }

        if (memberCount == 0) {
            System.out.println("Belum ada member yang terdaftar.");
        }
    }

    public void showadmin() {
        System.out.println("=== DAFTAR ADMIN ===");
        int adminCount = 0;
        for (UserAccount u : userList) {
            // Cek apakah user saat ini adalah instance dari kelas userwithpassword
            if (u instanceof UserWithPassword) {
                System.out.println("ID: " + u.getId() + ", Username: " + u.getUsername());
                adminCount++;
            }
        }

        if (adminCount == 0) {
            System.out.println("Belum ada admin yang terdaftar.");
        }
    }

    public void showowner() {
        System.out.println("=== Owner ===");
        int ownerCount = 0;
        for (UserAccount u : userList) {
            // Cek apakah user saat ini adalah instance dari kelas owneraccount
            if (u instanceof owneraccount) {
                System.out.println("ID: " + u.getId() + ", Username: " + u.getUsername());
                ownerCount++;
            }
        }

        if (ownerCount == 0) {
            System.out.println("Belum ada owner yang terdaftar.");
        }
    }

    public Basicmember getMemberByUsername(String username) {
        for (UserAccount u : userList) {
            if (u instanceof Basicmember && u.getUsername().equals(username)) {
                return (Basicmember) u;
            }
        }
        return null;
    }
    // Tambahkan metode ini di dalam kelas accountmanager.java

    public UserAccount findUserById(String id) {
        for (UserAccount u : userList) {
            // Cari berdasarkan ID atau Username, sesuaikan dengan kebutuhan
            if (u.getId().equals(id) || u.getUsername().equals(id)) {
                return u;
            }
        }
        return null; // Return null jika tidak ditemukan
    }

    // public void useradmin() {
    // for (UserAccount u : userList) {
    // UserWithPassword uwp = (UserWithPassword) u;
    // System.out.print(
    // "ID: " + uwp.getId() + ", Username: " + uwp.getUsername() + ", Password: " +
    // uwp.getPassword());

    // }
    // System.out.println();
    // }

    private String generateId() {
        String yearmonth = today.getYear() + "-" + String.format("%02d", today.getMonthValue());
        return yearmonth + "-" + userList.size();
    }

    public boolean login(String username, String password) {
        for (UserAccount u : userList) {
            if (u instanceof Basicmember) {
                Basicmember uwp = (Basicmember) u;
                if (u.getUsername().equals(username) && uwp.getpassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteBasicmember(String username) {
        for (int i = 0; i < userList.size(); i++) {
            UserAccount u = userList.get(i);
            if (u instanceof Basicmember && u.getUsername().equals(username)) {
                userList.remove(i);
                System.out.println("Member " + username + " berhasil dihapus.");
                return;
            } else if (u instanceof Basicmember && u.getUsername().equals(username)) {
                System.out.print("Masukkan password: ");
                String pw = sc.next();
                Basicmember userpw = (Basicmember) u;
                if (userpw.getpassword().equals(pw)) {
                    userList.remove(i);
                    System.out.println("akun admin dihapus");
                } else {
                    System.out.println("Password salah. Tidak bisa mengganti");
                }
            }
            return;
        }
        System.out.println("Basic user tidak ditemukan.");
    }

    public void updateBasicmember(String oldUser, String newUser) {
        for (UserAccount u : userList) {
            if (u instanceof Basicmember && u.getUsername().equals(oldUser)) {
                u.setUsername(newUser);
                System.out.println("Member berhasil diupdate.");
                return;
            } else if (u instanceof Basicmember && u.getUsername().equals(oldUser)) {
                System.out.print("Masukkan password: ");
                String pw = sc.next();
                Basicmember userpw = (Basicmember) u;
                if (userpw.getpassword().equals(pw)) {
                    userpw.setUsername(newUser);
                    System.out.println("Nama Admin berhasil diubah");
                } else {
                    System.out.println("Password salah. Tidak bisa mengganti");
                }
                return;
            }
            return;
        }
    }

    public boolean isMember(String idPembeli) {
        for (UserAccount u : userList) {
            if (u instanceof Basicmember) {
                Basicmember uwp = (Basicmember) u;
                if (uwp.getId().equalsIgnoreCase(idPembeli)) {
                    return true;
                }
            }
            // if (u.getId().equalsIgnoreCase(idPembeli)) {
            // return true;

            // }
        }
        return false;
    }

    public boolean namamember(String namaPembeli) {
        for (UserAccount u : userList) {
            if (u.getUsername().equalsIgnoreCase(namaPembeli)) {
                return true;

            }
        }
        return false;
    }

    public UserWithPassword loginAdmin(String username, String password) {
        for (UserAccount u : userList) {
            if (u instanceof UserWithPassword) {
                UserWithPassword admin = (UserWithPassword) u;
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    return admin;
                }
            }
        }
        return null;
    }

    public owneraccount loginOwner(String username, String password) {
        for (UserAccount u : userList) {
            if (u instanceof owneraccount) {
                owneraccount owner = (owneraccount) u;
                if (owner.getUsername().equals(username) && owner.getPassword().equals(password)) {
                    return owner;
                }
            }
        }
        return null;
    }

}
