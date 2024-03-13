package backend.hobbiebackend.model.entities;

import jakarta.persistence.*;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "hobbies")
@Setter
public class Hobby extends BaseEntity {

    private String contactInfo;

    private String creator;

    private String description;

    private String img1ID;

    private String img2ID;

    private String img3ID;

    private String galleryImageUrl1;

    private String galleryImageUrl2;

    private String galleryImageUrl3;

    private String intro;

    private String name;

    private BigDecimal price;

    private String profileImageUrl;

    private String profileImgId;

    private String slogan;

    private Category category;

    private Location location;

    @Column(name = "contact_info", columnDefinition = "TEXT")
    public String getContactInfo() {
        return contactInfo;
    }
    @Column
    public String getCreator() {
        return creator;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }
    @Column(name = "img1_id")
    public String getImg1ID() {
        return img1ID;
    }
    @Column(name = "img2_id")
    public String getImg2ID() {
        return img2ID;
    }
    @Column(name = "img3_id")
    public String getImg3ID() {
        return img3ID;
    }
    @Column(name = "gallery_image_url_1")
    public String getGalleryImageUrl1() {
        return galleryImageUrl1;
    }
    @Column(name = "gallery_image_url_2")
    public String getGalleryImageUrl2() {
        return galleryImageUrl2;
    }
    @Column(name = "gallery_image_url_3")
    public String getGalleryImageUrl3() {
        return galleryImageUrl3;
    }
    @Column(columnDefinition = "TEXT")
    public String getIntro() {
        return intro;
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }
    @Column(name = "profile_image_url")
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    @Column(name = "profile_img_id")
    public String getProfileImgId() {
        return profileImgId;
    }
    @Column
    public String getSlogan() {
        return slogan;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Category getCategory() {
        return category;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Location getLocation() {
        return location;
    }
}
