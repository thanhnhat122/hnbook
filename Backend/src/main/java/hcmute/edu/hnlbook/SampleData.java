package hcmute.edu.hnlbook;

import hcmute.edu.hnlbook.model.*;
import hcmute.edu.hnlbook.repository.OrderItemRepository;
import hcmute.edu.hnlbook.repository.OrderRepository;
import hcmute.edu.hnlbook.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleData {
  private static final Logger logger = LoggerFactory.getLogger(SampleData.class);

  @Bean
  CommandLineRunner initDatabase(UserService userService,
                                 BookService bookService,
                                 AuthorService authorService,
                                 PublisherService publisherService,
                                 ReviewService reviewService,
                                 OrderRepository orderRepository,
                                 OrderItemRepository orderItemRepository) {

    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {

        User user = new User("nhat@gmail.com", "Nhat", "Thanh", "Tan Phu", "HCM", "0869792657", "nhat123", User.RoleEnum.ROLE_AD);
        logger.info("insert data: " + userService.insertUser(user));

        User user1 = new User("long@gmail.com","Long","Hoang","Binh Thuy","CT","0934088208","long123", User.RoleEnum.ROLE_NV);
        logger.info("insert data: " + userService.insertUser(user1));

        User user2 = new User("hung@gmail.com","Hung","Gia","150, đường Số 11, phường Tăng Nhơn Phú B, quận 9","thành phố Hồ Chí Minh","6871937185","hung123", User.RoleEnum.ROLE_KH);
        logger.info("insert data: " + userService.insertUser(user2));

        User user3 = new User("phuoc@gmail.com","Phuoc","Thanh","XYZ","TP","6855557185","phuoc123", User.RoleEnum.ROLE_KH);
        logger.info("insert data: " + userService.insertUser(user3));

        Book book1 =
            new Book(
                1,
                1,
                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
                "978-333-2-08424-7",
                Book.genreEnum.Light_Novel,
                Book.typeEnum.Bia_Mem,
                2020,
                135_000.0,
                100,
                10,
                "18 x 13 cm",
                426,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg",
                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
                    "\n" +
                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
                    "\n" +
                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
                    "\n" +
                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
        logger.info("insert data: " + bookService.insert(book1));
        Book book2 =
                new Book(
                        2,
                        2,
                        "Sách Giáo Khoa Bộ Lớp 12 - Sách Bài Học (Bộ 14 Cuốn)",
                        "978-222-1-08456-7",
                        Book.genreEnum.Sach_Giao_Khoa,
                        Book.typeEnum.Bia_Mem,
                    2022,
                        180_000.0,
                        100,
                        15,
                        "24 x 17 x 8.4 cm",
                        0,
                        "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg",
                        "Bao Gồm: 1. Giáo dục Quốc phòng - An ninh 12, " +
                            "2. Giải tích 12, " +
                            "3. Hình học 12, " +
                            "4. Vật lí 12, " +
                            "5. Hoá học 12, " +
                            "6. Sinh học 12, " +
                            "7. Công nghệ 12, " +
                            "8. Ngữ văn 12/1, " +
                            "9. Ngữ văn 12/2, " +
                            "10. Lịch sử 12, " +
                            "11. Địa lí 12, " +
                            "12. Giáo dục công dân 12, " +
                            "13. Tin học 12, " +
                            "14. Tiếng Anh 12");
        logger.info("insert data: " + bookService.insert(book2));

        Book book4 =
                new Book(
                        4,
                        3,
                        "Conan Hoạt Hình Màu - Kẻ Hành Pháp Zero - Tập 1",
                        "979-300-2-09999-9",
                        Book.genreEnum.Manga,
                        Book.typeEnum.Bia_Mem,
                        2020,
                        50_000.0,
                        10,
                        20,
                        "13 x 18 cm",
                        208,
                        "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg",
                        "Ngay trước thềm Hội nghị Thượng đỉnh Tokyo, một vụ nổ lớn đã xảy ra tại công trình khổng lồ, nơi sẽ trở thành địa điểm tổ chức hội nghị!! Hiện trường vụ nổ ấy thấp thoáng bóng dáng Toru AMURO, thành viên của tổ chức bí mật thường gọi là “ZERO”, thuộc Cơ quan Cảnh sát Quốc gia Nhật Bản. Conan cảm thấy có gì đó kì lạ ở con người luôn hành động trong bóng tối ấy!!\n" +
                            "\n" +
                            "Giữa bối cảnh đó, những dấu vân tay tại vật chứng tìm thấy tại hiện trường vụ nổ đã được xác định là trùng khớp với vân tay của Mori KOGORO, cựu cảnh sát từng thuộc Tổ điều tra số 1. Xoay quanh vụ bắt bớ nghi phạm Kogoro, AMURO bắt đầu hành động như thể đối đầu với Conan… Đằng sau những hành động kì lạ ấy, liệu Amuro sẽ là bạn hay thù!?");
        logger.info("insert data: " + bookService.insert(book4));

        Book book5 =
            new Book(
                5,
                4,
                "Về Chuyện Tôi Chuyển Sinh Thành Slime - Tập 1",
                "978-333-2-05524-7",
                Book.genreEnum.Light_Novel,
                Book.typeEnum.Bia_Mem,
                2020,
                135_000.0,
                50,
                30,
                "18 x 13 cm",
                456,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg",
                "Về Chuyện Tôi Chuyển Sinh Thành Slime\n" +
                    "\n" +
                    "Mikami Satoru – một nhân viên văn phòng bình thường - đã ba mươi bảy cái xuân xanh nhưng vẫn còn là một thanh niên trai tân chính hiệu. Một ngày nọ, vì bảo vệ đồng nghiệp của mình, anh bị một tên điên cầm dao đâm trên phố. Trong những giây phút cuối đời, khi phải lên đường sang thế giới bên kia trong tình trạng “hàng còn nguyên tem” và trong đầu anh không ngừng vang lên một giọng nói thông báo về một loạt những năng lực khó hiểu, anh đã thề rằng: Nếu được tái sinh, anh nhất định sẽ thay đổi, sẽ chủ động tán tỉnh tất cả những người mà mình gặp, nhiệt tình theo đuổi con mồi rồi lao vào “ăn sạch”…\n" +
                    "\n" +
                    "Nhưng trớ trêu thay khi tỉnh lại, Satoru lại không được “tái sinh” thành một anh chàng đẹp trai bảnh bao cuốn hút như ý nguyện, mà lại thành một thứ sinh vật èo uột trơn tuột, không thể nhìn được, nghe được, hay nói được.\n" +
                    "\n" +
                    "“Tôi cũng chẳng biết bản thân trông như thế nào. Tôi cảm thấy cơ thể mình cứ… trơn tuột, giống như thạch vậy, hệt như hình dáng của loại “quái vật” đó. Thực ra tôi cũng đã nghĩ đến khả năng này từ nãy rồi.\n" +
                    "\n" +
                    "Tôi thực sự đã… được tái sinh thành một con slime…”\n" +
                    "\n" +
                    "Dần dà chấp nhận số phận rằng bản thân đã bị biến thành một con Slime kỳ lạ, Mikami Satoru bắt đầu cuộc sống nhàn tản của mình tại dị giới. Tuy nhiên, từ khi tình cờ gặp được Bạo Phong Long Veldora – một con quái vật cấp “thiên tai” và nhận được cái tên  “Rimuru Tempest” thì vận mệnh của anh đã hoàn toàn thay đổi.\n" +
                    "\n" +
                    "Liệu một người được chuyển sinh vào cơ thể của một con slime mất đi thính giác, thị giác sẽ làm thế nào để sống sót tại dị giới đây? Và dù trước khi chết đã nhận được hai kỹ năng vô song là Đại Hiền Giả và Kẻ Săn Mồi giúp bản thân đặc biệt hơn hẳn các con slime thông thường nhưng Rimuru lại chưa nhận ra điều đó, anh sẽ làm gì để bảo vệ bản thân và thực hiện được mục tiêu “ăn sạch” của mình trong thế giới đầy rẫy những hiểm nguy này?\n" +
                    "\n" +
                    "“Về chuyện tôi chuyển sinh thành Slime” là series light novel được chắp bút bởi Fuse và vẽ minh họa bởi Mizt Vah đã gây được tiếng vang lớn với 20 triệu bản sách được bán ra tại Nhật. Cùng nội dung lôi cuốn và phần minh họa đỉnh cao, series đình đám này hứa hẹn sẽ đưa người đọc vào một cuộc hành trình hồi hộp hấp dẫn đến nghẹt thở ngay từ tập đầu tiên!\n");
        logger.info("insert data: " + bookService.insert(book5));

        Book book6 =
            new Book(
                1,
                1,
                "OVERLORD - Tập 5: Những Anh Hùng Vương Quốc",
                "978-222-2-05524-7",
                Book.genreEnum.Light_Novel,
                Book.typeEnum.Bia_Mem,
                2021,
                160_000.0,
                20,
                30,
                "18 x 13 cm",
                424,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
                "Suzuki Satoru, hay Ainz, một nhân viên văn phòng say mê YGGDRASIL vẫn đăng nhập để nhâm nhi những giây phút sau cuối trước khi game dừng hoạt động. Nhưng khi đã sẵn sàng tinh thần chia tay các NPC dù vô tri vô giác nhưng là những đứa con tinh thần của mình, anh lại không chủ động đăng xuất được nữa mà nhảy vọt sang dị thế giới.\n" +
                    "\n" +
                    "Ở thế giới mới xa lạ, ấp ủ mong muốn tìm lại đồng đội cũ, Ainz quyết định khiến danh tiếng guild Ainz Ooal Gown vang xa với những kế hoạch chinh phục táo bạo.\n" +
                    "\n" +
                    "Sau trận chiến với những Á nhân thằn lằn ở các làng mạc xa xôi, sang tập 5, phần đầu của “Những anh hùng Vương quốc”, sân khấu Overlord zoom cận cảnh vào những đường phố Vương quốc, nơi những âm mưu chính trị, toan tính, dục vọng cá nhân âm thầm len lỏi dưới vỏ bọc yên bình mà để bảo vệ nó nhiều con người chính trực đang gồng mình chiến đấu. Điểm thú vị là cái nhìn về thế giới trong Overlord không chỉ mở rộng trên phương diện địa lý, mà còn đào sâu vào tâm lý từ con người đến các NPC - giờ đây đã hữu tri hữu giác, để chất phiêu lưu hào hùng cứ thế âm ỉ, định hình từng chút một, chuẩn bị cho một cuộc càn quét phía sau.");
        logger.info("insert data: " + bookService.insert(book6));

//        Book book90 =
//            new Book(
//                1,
//                1,
//                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
//                "978-333-2-08400-7",
//                Book.genreEnum.Light_Novel,
//                Book.typeEnum.Bia_Mem,
//                2020,
//                135_000.0,
//                100,
//                10,
//                "18 x 13 cm",
//                426,
//                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
//                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
//                    "\n" +
//                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
//                    "\n" +
//                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
//                    "\n" +
//                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
//        logger.info("insert data: " + bookService.insert(book90));
//
//        Book book91 =
//            new Book(
//                1,
//                1,
//                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
//                "978-333-2-08411-7",
//                Book.genreEnum.Light_Novel,
//                Book.typeEnum.Bia_Mem,
//                2020,
//                135_000.0,
//                100,
//                10,
//                "18 x 13 cm",
//                426,
//                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
//                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
//                    "\n" +
//                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
//                    "\n" +
//                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
//                    "\n" +
//                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
//        logger.info("insert data: " + bookService.insert(book91));
//
//        Book book92 =
//            new Book(
//                1,
//                1,
//                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
//                "978-333-2-08422-7",
//                Book.genreEnum.Light_Novel,
//                Book.typeEnum.Bia_Mem,
//                2020,
//                135_000.0,
//                100,
//                10,
//                "18 x 13 cm",
//                426,
//                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
//                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
//                    "\n" +
//                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
//                    "\n" +
//                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
//                    "\n" +
//                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
//        logger.info("insert data: " + bookService.insert(book92));
//
//        Book book93 =
//            new Book(
//                1,
//                1,
//                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
//                "978-333-2-08433-7",
//                Book.genreEnum.Light_Novel,
//                Book.typeEnum.Bia_Mem,
//                2020,
//                135_000.0,
//                100,
//                10,
//                "18 x 13 cm",
//                426,
//                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
//                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
//                    "\n" +
//                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
//                    "\n" +
//                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
//                    "\n" +
//                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
//        logger.info("insert data: " + bookService.insert(book93));
//
//        Book book94 =
//            new Book(
//                1,
//                1,
//                "OVERLORD - Tập 2: Chiến Binh Bóng Tối",
//                "978-333-2-08444-7",
//                Book.genreEnum.Light_Novel,
//                Book.typeEnum.Bia_Mem,
//                2020,
//                135_000.0,
//                100,
//                10,
//                "18 x 13 cm",
//                426,
//                "https://res.cloudinary.com/drwc3s5id/image/upload/v1665484161/hnlbook_book/978-333-2-08424-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484369/hnlbook_book/978-222-1-08456-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1665484431/hnlbook_book/979-300-2-09999-9.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666700603/hnlbook_book/978-333-2-05524-7.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1666854347/hnlbook_book/978-222-2-05524-7.jpg",
//                "Vào ngày hoạt động cuối cùng của game YGGDRASIL, do hiện tượng bí ẩn nào đó, một người chơi là Momonga trong tạo hình nhân vật bộ xương tự nhiên bị dịch chuyển tới một thế giới xa lạ. \n" +
//                    "\n" +
//                    "Đã tám ngày trôi qua. Suốt tám ngày này, Momonga, giờ đổi tên thành Ainz, đã thăm thú toàn bộ lăng mộ Nazarick, xem xét tình hình các thuộc hạ của mình. Sau khi xác nhận rằng nơi đây chẳng khác mấy so với thế giới game, Ainz quyết định đã đến lúc tiến hành bước tiếp theo, là mở rộng phạm vi khám phá và chinh phục.\n" +
//                    "\n" +
//                    "Dẫn theo một hầu gái hộ vệ, anh tìm đến thành phố trong vai trò người chuyên diệt quái, mà người ta vẫn gọi là “mạo hiểm giả”.\n" +
//                    "\n" +
//                    "Không biết chữ, không có tiền, tìm việc làm, cẩn trọng thăm dò môi trường mới, gặp gỡ nhiều nhân vật thuộc nhiều thành phần, đi săn một sinh vật pháp thuật hùng mạnh, và đáng kể nhất là bắt đầu công khai bộc lộ tài năng… Ainz cứ thế đặt chân vào một chuyến phiêu lưu mới.\n");
//        logger.info("insert data: " + bookService.insert(book94));

        Author author = new Author("Kugane", "Maruyama");
        logger.info("insert data: " + authorService.insert(author));
        Author author1 = new Author("Bộ Giáo Dục Và Đào Tạo", "");
        logger.info("insert data: " + authorService.insert(author1));
        Author author2 = new Author("The Candy", "");
        logger.info("insert data: " + authorService.insert(author2));
        Author author3 = new Author("Aoyama", "Gosho");
        logger.info("insert data: " + authorService.insert(author3));
        Author author4 = new Author("Fuse, Mizt Vah", "");
        logger.info("insert data: " + authorService.insert(author4));
        Author author5= new Author("Nhiều Tác Giả", "");
        logger.info("insert data: " + authorService.insert(author5));
        Author author6= new Author("Lê Đỗ Quỳnh Hương", "");
        logger.info("insert data: " + authorService.insert(author6));
        Author author7= new Author("Minh Niệm", "");
        logger.info("insert data: " + authorService.insert(author7));
        Author author8= new Author("José Mauro de Vasconcelos", "");
        logger.info("insert data: " + authorService.insert(author8));
        Author author9= new Author("Paulo Coelho", "");
        logger.info("insert data: " + authorService.insert(author9));
        Author author10= new Author("Gege Akutami", "");
        logger.info("insert data: " + authorService.insert(author10));
        Author author11= new Author("Phan Chánh Dưỡng", "");
        logger.info("insert data: " + authorService.insert(author11));
        Author author12= new Author("Phil Knight", "");
        logger.info("insert data: " + authorService.insert(author12));
        Author author13= new Author("The Windy", "");
        logger.info("insert data: " + authorService.insert(author13));
        Author author14= new Author("TRIVIETBOOK", "");
        logger.info("insert data: " + authorService.insert(author14));

        Publisher publisher = new Publisher("NXB Hồng Đức", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher));
        Publisher publisher1 = new Publisher("NXB Giáo Dục Việt Nam", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher1));
        Publisher publisher2 = new Publisher("NXB Kim Đồng", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher2));
        Publisher publisher3 = new Publisher("NXB Phụ Nữ Việt Nam", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher3));
        Publisher publisher4 = new Publisher("NXB Đại Học Sư Phạm TPHCM", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher4));
        Publisher publisher5 = new Publisher("NXB Tổng Hợp TPHCM", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher5));
        Publisher publisher6 = new Publisher("NXB Hội Nhà Văn", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher6));
        Publisher publisher7 = new Publisher("NXB Đà Nẵng", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher7));
        Publisher publisher8 = new Publisher("NXB Trẻ", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher8));
        Publisher publisher9 = new Publisher("NXB Đại Học Quốc Gia Hà Nội", "Việt Nam");
        logger.info("insert data: " + publisherService.insert(publisher9));

        Book b1 =
            new Book(
                6,
                5,
                "Sách Giáo Khoa Bộ Lớp 1 - Kết nối - Sách Bài Tập (Bộ 11 Cuốn)",
                "978-000-0-01589-7",
                Book.genreEnum.Sach_Giao_Khoa,
                Book.typeEnum.Bia_Mem,
                2022,
                126_000.0,
                100,
                15,
                "26.5 x 19 x 5.5 cm",
                0,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668478150/hnlbook_book/978-000-0-01589-7.jpg",
                "Bao Gồm: 1. VBT Đạo đức 1, " +
                    "2. VBT Hoạt động trải nghiệm 1 , " +
                    "3. VBT Âm nhạc 1, " +
                    "4. VBT Toán 1/1, " +
                    "5. VBT Toán 1/2, " +
                    "6. VBT Tiếng Việt 1/1, " +
                    "7. VBT Tiếng Việt 1/2, " +
                    "8. Tập viết 1/1, " +
                    "9. Tập viết 1/2, " +
                    "10. VBT Mĩ thuật 1, " +
                    "11. VBT Tự nhiên và Xã hội 1");
        logger.info("insert data: " + bookService.insert(b1));

        Book b2 =
            new Book(
                2,
                2,
                "Sách Giáo Khoa Bộ Lớp 11 - Sách Bài Tập (Bộ 11 Cuốn)",
                "978-000-0-01541-5",
                Book.genreEnum.Sach_Giao_Khoa,
                Book.typeEnum.Bia_Mem,
                2022,
                155_900.0,
                100,
                15,
                "24 x 17 cm",
                0,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668478477/hnlbook_book/978-000-0-01541-5.jpg",
                "Bao Gồm: 1. Bài tập Đại số và giải tích 11" +
                    "2. Bài tập Hình học 11," +
                    "3. Bài tập Vật lí 11," +
                    "4. Bài tập Hoá học 11," +
                    "5. Bài tập Ngữ văn 11/1," +
                    "6. Bài tập Ngữ văn 11/2," +
                    "7. Bài tập Tin học 11," +
                    "8. Bài tập Tiếng Anh 11," +
                    "9. Bài tập Sinh học 11," +
                    "10. Bài tập Lịch sử 11," +
                    "11. Bài tập Địa lí 11, " );
        logger.info("insert data: " + bookService.insert(b2));

        Book b3 =
            new Book(
                2,
                2,
                "Sách Giáo Khoa Bộ Lớp 9 - Sách Bài Tập (Bộ 7 Cuốn)",
                "978-000-0-01539-2",
                Book.genreEnum.Sach_Giao_Khoa,
                Book.typeEnum.Bia_Mem,
                2022,
                92_000.0,
                100,
                1,
                "24 x 17 x 4.2 cm",
                0,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668478686/hnlbook_book/978-000-0-01539-2.jpg",
                "Bao Gồm: 1. Bài tập Toán 9/1," +
                    "2. Bài tập Toán 9/2," +
                    "3. Bài tập Vật lí 9," +
                    "4. Bài tập Hóa học 9," +
                    "5. Bài tập Ngữ văn 9/1," +
                    "6. Bài tập Ngữ văn 9/2," +
                    "7. Bài tập Tiếng Anh 9 " );
        logger.info("insert data: " + bookService.insert(b3));

        Book b4 =
            new Book(
                7,
                6,
                "Thay Đổi Cuộc Sống Với Nhân Số Học",
                "978-508-6-85363-4",
                Book.genreEnum.Ky_Nang_Song,
                Book.typeEnum.Bia_Mem,
                2020,
                248_000.0,
                100,
                12,
                "20.5 x 14.5 cm",
                416,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668478877/hnlbook_book/978-508-6-85363-4.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1669015659/hnlbook_book/978-508-6-85363-4_1.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1669015994/hnlbook_book/978-508-6-85363-4_2.jpg, https://res.cloudinary.com/drwc3s5id/image/upload/v1669016006/hnlbook_book/978-508-6-85363-4_3.jpg",
                "Cuốn sách Thay đổi cuộc sống với Nhân số học là tác phẩm được chị Lê Đỗ Quỳnh Hương phát triển từ tác phẩm gốc “The Complete Book of Numerology” của tiến sỹ David A. Phillips, khiến bộ môn Nhân số học khởi nguồn từ nhà toán học Pythagoras trở nên gần gũi, dễ hiểu hơn với độc giả Việt Nam");
        logger.info("insert data: " + bookService.insert(b4));

        Book b5 =
            new Book(
                8,
                6,
                "Hiểu Về Trái Tim",
                "978-508-6-84990-3",
                Book.genreEnum.Ky_Nang_Song,
                Book.typeEnum.Bia_Mem,
                2019,
                138_000.0,
                100,
                1,
                "13 x 20.5 cm",
                480,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479084/hnlbook_book/978-508-6-84990-3.jpg",
                "Là một cuốn sách đặc biệt được viết bởi thiền sư Minh Niệm. Với phong thái và lối hành văn gần gũi với những sinh hoạt của người Việt, thầy Minh Niệm đã thật sự thổi hồn Việt vào cuốn sách nhỏ này.");
        logger.info("insert data: " + bookService.insert(b5));

        Book b6 =
            new Book(
                9,
                7,
                "Cây Cam Ngọt Của Tôi",
                "978-523-5-22835-1",
                Book.genreEnum.Tieu_Thuyet,
                Book.typeEnum.Bia_Mem,
                2020,
                108_000.0,
                100,
                2,
                "20 x 14.5 cm",
                244,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479236/hnlbook_book/978-523-5-22835-1.jpg",
                "Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.");
        logger.info("insert data: " + bookService.insert(b6));

        Book b7 =
            new Book(
                10,
                7,
                "Nhà Giả Kim (Tái Bản 2020)",
                "978-523-5-22627-2",
                Book.genreEnum.Tieu_Thuyet,
                Book.typeEnum.Bia_Mem,
                2020,
                79_000.0,
                100,
                2,
                "20.5 x 13 cm",
                227,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479317/hnlbook_book/978-523-5-22627-2.jpg",
                "Tiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản.");
        logger.info("insert data: " + bookService.insert(b7));

        Book b8 =
            new Book(
                11,
                3,
                "Chú Thuật Hồi Chiến - Tập 4 - Bản Thường - Tặng Kèm Obi Và Card Nhựa",
                "978-524-4-86793-0",
                Book.genreEnum.Manga,
                Book.typeEnum.Bia_Mem,
                2022,
                30_000.0,
                100,
                5,
                "17.6 x 11.3 cm",
                50,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479473/hnlbook_book/978-524-4-86793-0.jpg",
                "Tại hiện trường án mạng do chú linh gây ra, Itadori đã gặp gỡ Junpei, cả hai tâm đầu ý hợp. Nhưng Junpei lại tôn sùng chú linh Mahito, thủ phạm của vụ án. Mahito lợi dụng Junpei, hòng li gián cậu và Itadori");
        logger.info("insert data: " + bookService.insert(b8));

        Book b9 =
            new Book(
                12,
                8,
                "Ký Ức Theo Dòng Đời",
                "978-614-4-20155-8",
                Book.genreEnum.Kinh_Te,
                Book.typeEnum.Bia_Mem,
                2022,
                218_000.0,
                100,
                0,
                "20.5 x 13 x 1 cm",
                389,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479762/hnlbook_book/978-614-4-20155-8.jpg",
                "Cuốn sách là nguồn tài liệu quý giá, cẩm nang thuyết phục về tư duy sáng tạo, cách làm đột phá và tinh thần cống hiến cho xã hội của Phan Chánh Dưỡng cùng những trí thức thế hệ ông trong Nhóm Thứ Sáu – nghiên cứu và cố vấn chính sách kinh tế.");
        logger.info("insert data: " + bookService.insert(b9));

        Book b10 =
            new Book(
                13,
                9,
                "Gã Nghiện Giày - Tự Truyện Của Nhà Sáng Lập Nike",
                "978-497-4-15096-1",
                Book.genreEnum.Kinh_Te,
                Book.typeEnum.Bia_Mem,
                2017,
                160_000.0,
                100,
                10,
                "15.5 x 23 cm",
                452,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668479939/hnlbook_book/978-497-4-15096-1.jpg",
                "Một câu chuyện cuốn hút, và truyền cảm hứng.... 24 tuổi, lấy bằng MBA ở Đại học Stanford, trăn trở với những câu hỏi lớn của cuộc đời, băn khoăn không biết tiếp tục làm việc cho một tập đoàn lớn hay tạo dựng sư nghiệp riêng cho mình... 24 tuổi, năm 1962, Phil Knight quyết định rằng con đường khác thường mới là lựa chọn duy nhất dành cho ông...");
        logger.info("insert data: " + bookService.insert(b10));

        Book b11 =
            new Book(
                14,
                10,
                "Từ Điển Oxford Anh - Anh - Việt (Bìa Vàng) - Tái Bản",
                "978-524-6-91797-8",
                Book.genreEnum.Tu_Dien,
                Book.typeEnum.Bia_Cung,
                2018,
                298_000.0,
                100,
                10,
                "11.5 x 17.5 cm",
                1570,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668480061/hnlbook_book/978-524-6-91797-8.jpg",
                "Cuốn từ điển đặc biệt phù hợp với sinh viên và người học tiếng Anh, thể hiện ở những mục từ mới mẻ, cập nhật, những lời giải thích ngắn gọn mà rõ ràng, kèm với những ví dụ cụ thể mà sinh động, những mẫu câu, kết cấu phổ dụng mà đa dạng của mỗi mục từ nhằm giúp người đọc biết cách sử dụng từ ngữ chính xác, phù hợp. ");
        logger.info("insert data: " + bookService.insert(b11));

        Book b12 =
            new Book(
                15,
                1,
                "Từ Điển Anh Việt 200.000 Từ",
                "979-604-8-95394-2",
                Book.genreEnum.Tu_Dien,
                Book.typeEnum.Bia_Mem,
                2018,
                105_000.0,
                100,
                10,
                "18 x 18 cm",
                1212,
                "https://res.cloudinary.com/drwc3s5id/image/upload/v1668480344/hnlbook_book/979-604-8-95394-2.jpg",
                "Cuốn từ điển phù hợp với hầu hết các đối tượng đang theo học Anh văn, tuy nhiên hiệu quả học tập còn phụ thuộc vào việc bạn sử dụng cuốn từ điển như thế nào và hiểu được lợi ích mà cuốn từ điển mang đến cho bạn.  ");
        logger.info("insert data: " + bookService.insert(b12));

        Review review = new Review(1, "hung@gmail.com", 6, "Rất hay", 5, "");
        logger.info("insert data: " + reviewService.insert(review));

        Review review1 = new Review(2, "hung@gmail.com", 7, "Giao hàng nhanh", 4, "");
        logger.info("insert data: " + reviewService.insert(review1));

        Review review2 = new Review(4, "phuoc@gmail.com", 9, "...", 3, "");
        logger.info("insert data: " + reviewService.insert(review2));

        Review review3 = new Review(1, "phuoc@gmail.com", 10, "***", 2, "");
        logger.info("insert data: " + reviewService.insert(review3));

        Review review4 = new Review(6, "phuoc@gmail.com", 11, "@@", 1, "");
        logger.info("insert data: " + reviewService.insert(review4));

//        Review review5 = new Review(1, "hung@gmail.com", 6, "iii", 5);
//        logger.info("insert data: " + reviewService.insert(review5));
//
//        Review review6 = new Review(1, "hung@gmail.com", 6, "qqq", 5);
//        logger.info("insert data: " + reviewService.insert(review6));
//
//        Review review7 = new Review(1, "hung@gmail.com", 6, "www", 5);
//        logger.info("insert data: " + reviewService.insert(review7));
//
//        Review review8 = new Review(1, "hung@gmail.com", 6, "eee", 5);
//        logger.info("insert data: " + reviewService.insert(review8));
//
//        Review review9 = new Review(1, "hung@gmail.com", 6, "rrr", 5);
//        logger.info("insert data: " + reviewService.insert(review9));
//
//        Review review10 = new Review(1, "hung@gmail.com", 6, "ttt", 3);
//        logger.info("insert data: " + reviewService.insert(review10));
//
//        Review review11 = new Review(1, "hung@gmail.com", 6, "yyy", 2);
//        logger.info("insert data: " + reviewService.insert(review11));
//
//        Review review12 = new Review(1, "hung@gmail.com", 6, "uuu", 1);
//        logger.info("insert data: " + reviewService.insert(review12));
//
//        Review review13 = new Review(1, "hung@gmail.com", 6, "sss", 3);
//        logger.info("insert data: " + reviewService.insert(review13));
//
//        Review review14 = new Review(1, "hung@gmail.com", 6, "ddd", 4);
//        logger.info("insert data: " + reviewService.insert(review14));

        Order order1 = new Order("hung@gmail.com", Order.StatusEnum.Chua_Dat_Hang);
        logger.info("insert data: " + orderRepository.save(order1));

        OrderItem orderItem1 = new OrderItem(1,1,2);
        OrderItem orderItem2 = new OrderItem(1,3,5);
        OrderItem orderItem3 = new OrderItem(1,2,3);
        logger.info("insert data: " +orderItemRepository.save(orderItem1));
        logger.info("insert data: " +orderItemRepository.save(orderItem2));
        logger.info("insert data: " +orderItemRepository.save(orderItem3));

        Order order2 = new Order("hung@gmail.com", Order.StatusEnum.Cho_Xac_Nhan);
        logger.info("insert data: " + orderRepository.save(order2));

        OrderItem orderItem4 = new OrderItem(2,1,2);
        logger.info("insert data: " +orderItemRepository.save(orderItem4));

        Order order3 = new Order("hung@gmail.com", Order.StatusEnum.Dang_Giao);
        logger.info("insert data: " + orderRepository.save(order3));

        OrderItem orderItem5 = new OrderItem(3,1,2);
        logger.info("insert data: " +orderItemRepository.save(orderItem5));

        Order order4 = new Order("hung@gmail.com", Order.StatusEnum.Da_Nhan);
        logger.info("insert data: " + orderRepository.save(order4));

        OrderItem orderItem6 = new OrderItem(4,1,2);
        OrderItem orderItem7 = new OrderItem(4,2,1);
        OrderItem orderItem8 = new OrderItem(4,3,3);
        logger.info("insert data: " +orderItemRepository.save(orderItem6));
        logger.info("insert data: " +orderItemRepository.save(orderItem7));
        logger.info("insert data: " +orderItemRepository.save(orderItem8));

        Order order5 = new Order("phuoc@gmail.com", Order.StatusEnum.Da_Nhan);
        logger.info("insert data: " + orderRepository.save(order5));

        OrderItem orderItem9 = new OrderItem(5,4,5);
        OrderItem orderItem10 = new OrderItem(5,1,4);
        OrderItem orderItem11 = new OrderItem(5,6,1);
        logger.info("insert data: " +orderItemRepository.save(orderItem9));
        logger.info("insert data: " +orderItemRepository.save(orderItem10));
        logger.info("insert data: " +orderItemRepository.save(orderItem11));

        Order order6 = new Order("hung@gmail.com", Order.StatusEnum.Da_Huy);
        logger.info("insert data: " + orderRepository.save(order6));

        OrderItem orderItem12 = new OrderItem(6,1,2);
        logger.info("insert data: " +orderItemRepository.save(orderItem12));
      }
    };
  }
}
