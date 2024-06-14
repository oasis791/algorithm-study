#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <filename.java>"
  exit 1
fi

base_name=$(basename "$1" .java)

cat <<EOL > "$1"
import java.io.*;
import java.util.*;

public class $base_name {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    }
}
EOL

echo "Java file '$1' has been created."
