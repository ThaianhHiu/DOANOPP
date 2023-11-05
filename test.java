import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Interface định nghĩa các phương thức quản lý
interface QuanLy {
    void them();
    void sua();
    void xoa();
    void timKiem();
    void xuat();
}

// Lớp trừu tượng đại diện cho một loại bánh
abstract class Banh {
    protected String ten;
    protected double gia;

    public Banh(String ten, double gia) {
        this.ten = ten;
        this.gia = gia;
    }

    public abstract double tinhTien();

    public String getTen() {
        return ten;
    }

    public double getGia() {
        return gia;
    }
}

// Lớp Bánh ngọt kế thừa từ lớp Banh
class BanhNgot extends Banh {
    private int soLuong;

    public BanhNgot(String ten, double gia, int soLuong) {
        super(ten, gia);
        this.soLuong = soLuong;
    }

    public double tinhTien() {
        return gia * soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

// Lớp Bánh mì kế thừa từ lớp Banh
class BanhMi extends Banh {
    private String loai;

    public BanhMi(String ten, double gia, String loai) {
        super(ten, gia);
        this.loai = loai;
    }

    public double tinhTien() {
        return gia;
    }

    public String getLoai() {
        return loai;
    }
}

// Lớp Bánh kem kế thừa từ lớp Banh
class BanhKem extends Banh {
    private String loai;

    public BanhKem(String ten, double gia, String loai) {
        super(ten, gia);
        this.loai = loai;
    }

    public double tinhTien() {
        return gia;
    }

    public String getLoai() {
        return loai;
    }
}

// Lớp KhachHang đại diện cho một khách hàng
class KhachHang {
    private String ten;
    private String diaChi;

    public KhachHang(String ten, String diaChi) {
        this.ten = ten;
        this.diaChi = diaChi;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diaChi;
    }
}

// Lớp HoaDon đại diện cho một hóa đơn mua hàng
class HoaDon {
    private int maHoaDon;
    private KhachHang khachHang;
    private ArrayList<Banh> danhSachBanhs;

    public HoaDon(int maHoaDon, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.danhSachBanhs = new ArrayList<>();
    }

    public void themBanh(Banh banh) {
        danhSachBanhs.add(banh);
    }

    public void xuatHoaDon() {
        System.out.println("Hóa đơn số: " + maHoaDon);
        System.out.println("Tên khách hàng: " + khachHang.getTen());
        System.out.println("Địa chỉ khách hàng: " + khachHang.getDiaChi());
        System.out.println("Danh sách bánh:");
        double tongTien = 0;
        for (Banh banh : danhSachBanhs) {
            System.out.println("Tên bánh: " + banh.getTen());
            System.out.println("Giá: " + banh.tinhTien());
            tongTien += banh.tinhTien();
        }
        System.out.println("Tổng tiền: " + tongTien);
    }
}

// Lớp DanhSachBanh là lớp mảng các đối tượng Banh
class DanhSachBanh {
    private ArrayList<Banh> danhSachBanhs = new ArrayList<>();

    public void them(Banh banh) {
        danhSachBanhs.add(banh);
    }

    public void sua(String tenBanh, Banh banhMoi) {
        for (int i = 0; i < danhSachBanhs.size(); i++) {
            if (danhSachBanhs.get(i).getTen().equals(tenBanh)) {
                danhSachBanhs.set(i, banhMoi);
                System.out.println("Bánh đã được sửa.");
                return;
            }
        }
        System.out.println("Không tìm thấy bánh cần sửa.");
    }

    public void xoa(String tenBanh) {
        for (int i = 0; i < danhSachBanhs.size(); i++) {
            if (danhSachBanhs.get(i).getTen().equals(tenBanh)) {
                danhSachBanhs.remove(i);
                System.out.println("Bánh đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy bánh cần xóa.");
    }

    public void timKiem(String ten) {
        System.out.println("Kết quả tìm kiếm:");
        for (Banh banh : danhSachBanhs) {
            if (banh.getTen().contains(ten)) {
                System.out.println("Tên bánh: " + banh.getTen());
                System.out.println("Giá: " + banh.tinhTien());
            }
        }
    }

    public void xuat() {
        try {
            PrintWriter writer = new PrintWriter("danh_sach_banh.txt");
            for (Banh banh : danhSachBanhs) {
                writer.println("Tên bánh: " + banh.getTen());
                writer.println("Giá: " + banh.tinhTien());
                writer.println();
            }
            writer.close();
            System.out.println("Danh sách bánh đã được xuất ra file.");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file.");
        }
    }

    public void xuatToanBo() {
        System.out.println("Danh sách bánh:");
        for (Banh banh : danhSachBanhs) {
            System.out.println("Tên bánh: " + banh.getTen());
            System.out.println("Giá: " + banh.tinhTien());
        }
    }
}

public class test {
    public class Main {
        public static void main(String[] args) {
            DanhSachBanh danhSachBanh = new DanhSachBanh();
            Banh banh1 = new BanhNgot("Banh Kem Chocolate", 10, 5);
            Banh banh2 = new BanhMi("Banh Mi Gia Lai", 3, "Ngọt");
            Banh banh3 = new BanhKem("Banh Kem Trái Cây", 12, "Trái cây");
            danhSachBanh.them(banh1);
            danhSachBanh.them(banh2);
            danhSachBanh.them(banh3);
    
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                System.out.println("1. Thêm bánh");
                System.out.println("2. Sửa bánh");
                System.out.println("3. Xóa bánh");
                System.out.println("4. Tìm kiếm bánh");
                System.out.println("5. Xuất danh sách bánh");
                System.out.println("6. Xuất toàn bộ danh sách bánh");
                System.out.println("7. Thoát");
                System.out.print("Chọn tùy chọn: ");
    
                int choice = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng trống sau khi đọc số
    
                switch (choice) {
                    case 1:
                        System.out.println("1. Banh Ngot");
                        System.out.println("2. Banh Mi");
                        System.out.println("3. Banh Kem");
                        System.out.print("Chọn loại bánh: ");
                        int loaiBanh = scanner.nextInt();
                        scanner.nextLine(); // Đọc dòng trống sau khi đọc số
                        System.out.print("Nhập tên bánh: ");
                        String tenBanh = scanner.nextLine();
                        System.out.print("Nhập giá bánh: ");
                        double giaBanh = scanner.nextDouble();
                        scanner.nextLine(); // Đọc dòng trống sau khi đọc số
                        switch (loaiBanh) {
                            case 1:
                                System.out.print("Nhập số lượng: ");
                                int soLuong = scanner.nextInt();
                                scanner.nextLine(); // Đọc dòng trống sau khi đọc số
                                danhSachBanh.them(new BanhNgot(tenBanh, giaBanh, soLuong));
                                break;
                            case 2:
                                System.out.print("Nhập loại bánh mì: ");
                                String loaiBanhMi = scanner.nextLine();
                                danhSachBanh.them(new BanhMi(tenBanh, giaBanh, loaiBanhMi));
                                break;
                            case 3:
                                System.out.print("Nhập loại bánh kem: ");
                                String loaiBanhKem = scanner.nextLine();
                                danhSachBanh.them(new BanhKem(tenBanh, giaBanh, loaiBanhKem));
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                        break;
                    case 2:
                        System.out.print("Nhập tên bánh cần sửa: ");
                        String tenBanhSua = scanner.nextLine();
                        System.out.print("Nhập tên mới: ");
                        String tenMoi = scanner.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double giaMoi = scanner.nextDouble();
                        scanner.nextLine(); // Đọc dòng trống sau khi đọc số
                        danhSachBanh.sua(tenBanhSua, new BanhNgot(tenMoi, giaMoi, 0));
                        break;
                    case 3:
                        System.out.print("Nhập tên bánh cần xóa: ");
                        String tenBanhXoa = scanner.nextLine();
                        danhSachBanh.xoa(tenBanhXoa);
                        break;
                    case 4:
                        System.out.print("Nhập thông tin cần tìm: ");
                        String timKiem = scanner.nextLine();
                        danhSachBanh.timKiem(timKiem);
                        break;
                    case 5:
                        danhSachBanh.xuat();
                        break;
                    case 6:
                        danhSachBanh.xuatToanBo();
                        break;
                    case 7:
                        System.out.println("Kết thúc chương trình.");
                        System.exit(0);
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            }
        }
    }
}
