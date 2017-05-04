package com.raf.fwk.util;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tourneurr
 *
 */
@Slf4j
public class ImageExtracter {

  /**
   * @param args
   */
  public static void main(String[] args) {
    String name = "C:/PROG/imperial/dice.jpg";
    try {
      extract(name);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void extract(String filename) throws IOException {
    int sizeX = 140;
    int sizeY = 140;

    int sides = 6;
    String[] names = { "red", "blue", "green", "yellow", "black", "white" };
    Point[] points = { new Point(7, 16), new Point(150, 16), new Point(296, 16), new Point(437, 16), new Point(580, 14), new Point(722, 13), 
        new Point(8, 159), new Point(150, 157), new Point(295, 157), new Point(439, 155), new Point(582, 153), new Point(724, 153),
        new Point(8, 300), new Point(150, 300), new Point(295, 300), new Point(439, 299), new Point(582, 299), new Point(724, 296),
        new Point(8, 443), new Point(150, 443), new Point(295, 443), new Point(439, 443), new Point(582, 443), new Point(722, 443),
        new Point(8, 593), new Point(150, 593), new Point(295, 593), new Point(439, 593), new Point(582, 593), new Point(724, 593),
        new Point(8, 741), new Point(150, 741), new Point(295, 741), new Point(439, 741), new Point(582, 741), new Point(724, 741)};

    int startPointX;
    int startPointY;
    Point point;
    int cursor = 0;

    BufferedImage image = ImageIO.read(new File(filename));
    log.debug("File load {}", filename);
    log.debug("Image : height {} - width {}", image.getHeight(), image.getWidth());

    for (int dice = 0; dice < names.length; dice++) {
      for (int index = 0; index < sides; index++) {
        point = points[cursor];
        startPointX = point.x;
        startPointY = point.y;
        cursor++;
        log.debug("Extraxt : Origin {}-{} End {}-{}", startPointX, startPointY, sizeX, sizeY);
        BufferedImage out = image.getSubimage(startPointX, startPointY, sizeX, sizeY);
        ImageIO.write(out, "jpg", new File(names[dice] + "-" + index + ".jpg"));
      }
    }

  }
}
