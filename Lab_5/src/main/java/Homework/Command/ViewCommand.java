package Homework.Command;

import Homework.Catalog;
import Homework.Objects.Document;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@NoArgsConstructor
public class ViewCommand extends CommandAdapter {
    @Override
    public void execute(Catalog catalog, String id) throws URISyntaxException, IOException {
        Document byId = catalog.findById(id);
        Desktop desktop = Desktop.getDesktop();
        System.out.println(byId.getPath());
        if (byId.getPath().contains("https://") || byId.getPath().contains("http://")) {
            URI link;
            link = new URI(byId.getPath());
            desktop.browse(link);
        } else {
            File file = new File(byId.getPath());
            desktop.open(file);
        }

    }
}
