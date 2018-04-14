#!/bin/bash
# My first script

gource -1920x1080 \
--camera-mode overview \
--caption-file /caption/MasterCap.txt \
--caption-size 30 \
--caption-duration 6 \
--title "DATA KONTRAKAN" \
--user-image-dir /avatar/ \
--max-user-speed 170 \
--user-friction 0.57 \
--user-scale 0.8 \
--bloom-multiplier 0.1 \
--font-size 24 \
--logo .gource/logo.png \
--multi-sampling --colour-images --key \
-s 30 \
-c 1.5 \
-a 0.1 \
#! -o - | ffmpeg -y -r 120 -f image2pipe -vcodec ppm -i - -vcodec libx264 -preset ultrafast -pix_fmt yuv444p -crf 1 -threads 12 -bf 0 gource.mp4

