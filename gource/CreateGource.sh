#!/bin/bash
# My first script

function CreateCaption()
{
    echo Creating Caption File...
    sleep 2
    cd caption
    
    if ["$1" == "n" ] || ["$1" == "N"]; then
        python3 make_caption.py > nul
    else
        python3 make_caption.py
    fi
    
    rm nul
    sleep 0.5
    cd ..
    echo Caption File Created
}

clear
read -p "Debug ? [y/n] >> " ask
echo

CreateCaption $ask

#Gource Video Making
sleep 2
echo Running Gource Video...
sleep 2
gource -1920x1080 \
--camera-mode overview \
--caption-file caption/MasterCaption \
--caption-size 30 \
--caption-duration 6 \
--title "DATA KONTRAKAN" \
--user-image-dir avatar/ \
--max-user-speed 170 \
--user-friction 0.57 \
--user-scale 0.8 \
--bloom-multiplier 0.1 \
--font-size 24 \
--logo logo.png \
--multi-sampling --colour-images --key \
-s 5 \
-e 0.4 \
-a 0.01 \
# -o - | ffmpeg -y -r 120 -f image2pipe -vcodec ppm -i - -vcodec libx264 -preset ultrafast -pix_fmt yuv444p -crf 1 -threads 12 -bf 0 gource.mp4

echo Exitting...
cd caption
rm MasterCaption
rm OriginalCaption
