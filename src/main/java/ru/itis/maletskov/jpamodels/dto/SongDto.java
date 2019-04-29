package ru.itis.maletskov.jpamodels.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jpamodels.User;

@Data
@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
public class SongDto {
    private Long id;
    private String title;
    private String tag;
    private User author;
    private String audioFileName;
    private String imgFileName;
    private Long likes;
    private Boolean meLiked;

    public SongDto(Song song, Long likes, Boolean meLiked) {
        this.id = song.getId();
        this.title = song.getTitle();
        this.tag = song.getTag();
        this.author = song.getAuthor();
        this.audioFileName = song.getAudioFileName();
        if (song.getSongImg() != null) {
            this.imgFileName = song.getSongImg().getFileName();
        }
        this.likes = likes;
        this.meLiked = meLiked;
    }
}
