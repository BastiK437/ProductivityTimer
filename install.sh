#!/bin/bash

PROGRAM_DIR=Program

while [ true ]
do
    read -p "Enter install dir (full path): " INSTALL_DIR

    if [[ ! -d "$INSTALL_DIR" ]]
    then
        echo "$INSTALL_DIR not exists on your filesystem."
    else 
        break;
    fi

done 

if [[ ! -d "$INSTALL_DIR/ProductivityTimer" ]]
then
    mkdir $INSTALL_DIR/ProductivityTimer
fi

cp -r $PROGRAM_DIR/* $INSTALL_DIR/ProductivityTimer/

FILE="productivity.desktop"

/bin/cat <<EOM >$FILE
[Desktop Entry]
Name=Productivity Timer
Comment=Little stopwatch application to measure productivity on each day
Exec=$INSTALL_DIR/ProductivityTimer/start.sh
Icon=$INSTALL_DIR/ProductivityTimer/icon.png
Terminal=false
Type=Application
Path=$INSTALL_DIR
StartupNotify=false
EOM

mv productivity.desktop $HOME/.local/share/applications/
