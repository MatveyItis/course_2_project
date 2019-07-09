package ru.itis.maletskov.form;

import lombok.Getter;
import lombok.Setter;
import ru.itis.maletskov.model.Song;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SongForm {
    @NotNull
    private String title;
    @NotNull
    private String tag;

    public static Song fromFormToSong(SongForm form) {
        Song song = new Song();
        song.setTitle(form.getTitle());
        song.setTag(form.getTag());
        return song;
    }
}
