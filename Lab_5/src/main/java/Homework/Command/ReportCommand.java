package Homework.Command;

import Homework.Catalog;
import lombok.NoArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
public class ReportCommand extends CommandAdapter {
    public void execute(Catalog catalog) throws IOException {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < catalog.getContains().size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", catalog.getContains().get(i).getName());
            map.put("id", catalog.getContains().get(i).getId());
            map.put("path", catalog.getContains().get(i).getPath());
            list.add(map);
        }
        VelocityContext context = new VelocityContext();
        context.put("nume_catalog", catalog.getName());
        context.put("infoList", list);
        Template t = ve.getTemplate("src/main/resources/vtemplates/class.vm");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String nameofFiles = catalog.getName() + "_Content.html";
        BufferedOutputStream file = null;
        file = new BufferedOutputStream(new FileOutputStream(nameofFiles), 1024);
        file.write(writer.toString().getBytes());
        file.flush();
        file.close();
        Desktop desktop = Desktop.getDesktop();
        File fileTwo = new File(nameofFiles);
        desktop.open(fileTwo);
    }
}
