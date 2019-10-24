package aericks1.example.playbook;

import android.media.Image;

import java.util.UUID;

public class Play {
    private UUID mId;
    private String mtitle;
    private String mDescription;
    private Image mImage;

    public Play(){
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mtitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Image getImage() {
        return mImage;
    }

    public void setTitle(String title) {
        mtitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setImage(Image image){
        mImage = image;
    }
}
