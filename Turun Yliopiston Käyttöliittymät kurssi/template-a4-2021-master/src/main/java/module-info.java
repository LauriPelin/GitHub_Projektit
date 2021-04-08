// declarate module imports and exports here
// see: https://tech.utugit.fi/soft/tools/lectures/tko8971/build/jpms/

module fi.utu.tech.gui.javafx {
  //requires java.desktop;
  requires transitive javafx.base;
  requires transitive javafx.fxml;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive javafx.media;
  requires transitive javafx.web;
  exports fi.utu.tech.gui.javafx;
  exports fi.utu.tech.gui.javafx.assignment1;
  exports fi.utu.tech.gui.javafx.assignment2;
  exports fi.utu.tech.gui.javafx.assignment3;
  exports fi.utu.tech.gui.javafx.assignment4;
  exports fi.utu.tech.gui.javafx.assignment5;
  exports fi.utu.tech.gui.javafx.assignment6;
  exports fi.utu.tech.gui.javafx.assignment7;
  exports fi.utu.tech.gui.javafx.assignment8;
  opens fi.utu.tech.gui.javafx;
  opens fi.utu.tech.gui.javafx.assignment1;
  opens fi.utu.tech.gui.javafx.assignment2;
  opens fi.utu.tech.gui.javafx.assignment3;
  opens fi.utu.tech.gui.javafx.assignment4;
  opens fi.utu.tech.gui.javafx.assignment5;
  opens fi.utu.tech.gui.javafx.assignment6;
  opens fi.utu.tech.gui.javafx.assignment7;
  opens fi.utu.tech.gui.javafx.assignment8;






}
