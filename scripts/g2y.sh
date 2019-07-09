#!/bin/bash

sed -i.bk 's/💚/💛/g' index.php
rm -f *.bk
