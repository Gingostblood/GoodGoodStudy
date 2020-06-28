package com.gingost.nginx_img.domain;

        import lombok.Data;
        import lombok.experimental.Accessors;

        import javax.persistence.*;

/**
 * @author:lezzy
 * @Date:2020/3/30 10:55
 */
@Entity
@Table(name = "exingimg")
@Data
@Accessors(chain = true)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title_url")
    private String title;

    @Column(name = "content_url")
    private String content;
}
