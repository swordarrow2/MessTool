package com.meng.tools.update;

import java.util.*;

public class UpdateNotes {

    /*
     *@author 清梦
     *@date 2024-08-27 22:25:52
     */
    public static final String TAG = "UpdateNotes";

    public LinkedList<Node> noteList;

    public Node getLastNote() {
        return noteList.getLast();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : noteList) {
            builder.append("v").append(node.version).append("\n");
            for (String s : node.note) {
                builder.append("●").append(s).append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static class Node {
        public String version;
        public int versionCode;
        private List<String> note;

        public Node() {

        }

        public Node(String version, String... note) {
            this.version = version;
            this.note = new LinkedList<String>();
            Collections.addAll(this.note, note);
        }

        public Node(String version, List<String> note) {
            this.version = version;
            this.note = note;
        }

    }
}
