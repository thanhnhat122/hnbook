package hcmute.edu.hnlbook.model;

import java.util.List;

public class Statistic {
    /*
     * Doanh thu tính theo tháng
     * tháng
     * năm
     * Tổng số sách bán được    xong
     * Tổng số tiền bán được    xong
     * List sách đã bán và số lượng     | cần update số lượng đã bán khi mua hàng
     * List best seller         xong
     * Thể loại bán chạy        xong
     *
     * Số lượng tài khoản đăng ký
     * Số lượng tài khoản mua hàng
     * List tài khoản mua hàng
     */
    private int month;
    private int year;
    private int totalBookSold;
    private int totalEarn;
    private List<Book> listBookSold;
    private List<Book> bestBookSeller;
    private List<Book.genreEnum> bestGenreSeller;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalBookSold() {
        return totalBookSold;
    }

    public void setTotalBookSold(int totalBookSold) {
        this.totalBookSold = totalBookSold;
    }

    public int getTotalEarn() {
        return totalEarn;
    }

    public void setTotalEarn(int totalEarn) {
        this.totalEarn = totalEarn;
    }

    public List<Book> getListBookSold() {
        return listBookSold;
    }

    public void setListBookSold(List<Book> listBookSold) {
        this.listBookSold = listBookSold;
    }

    public List<Book> getBestBookSeller() {
        return bestBookSeller;
    }

    public void setBestBookSeller(List<Book> bestBookSeller) {
        this.bestBookSeller = bestBookSeller;
    }

    public List<Book.genreEnum> getBestGenreSeller() {
        return bestGenreSeller;
    }

    public void setBestGenreSeller(List<Book.genreEnum> bestGenreSeller) {
        this.bestGenreSeller = bestGenreSeller;
    }

    public Statistic() {
    }

    public Statistic(int month, int year, int totalBookSold, int totalEarn, List<Book> listBookSold, List<Book> bestBookSeller, List<Book.genreEnum> bestGenreSeller) {
        this.month = month;
        this.year = year;
        this.totalBookSold = totalBookSold;
        this.totalEarn = totalEarn;
        this.listBookSold = listBookSold;
        this.bestBookSeller = bestBookSeller;
        this.bestGenreSeller = bestGenreSeller;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "month=" + month +
                ", year=" + year +
                ", totalBookSold=" + totalBookSold +
                ", totalEarn=" + totalEarn +
                ", listBookSold=" + listBookSold +
                ", bestBookSeller=" + bestBookSeller +
                ", bestGenreSeller=" + bestGenreSeller +
                '}';
    }
}
