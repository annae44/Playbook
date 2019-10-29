package aericks1.example.playbook;

import java.util.UUID;

public class Play {
    private UUID mId;
    private String mtitle;
    private String mDescription;

    public Play(){
        //mId = UUID.randomUUID();
        this(UUID.randomUUID());
    }

    public Play(UUID id) {
        mId = id;
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

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public void setTitle(String title) {
        mtitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
