echo "Welcome to the case example!"
echo "Without a select statement, you must get the spelling/case exact."
read -p "What format do you prefer? (tape/cd/mp3/lp) " FORMAT
echo "You said $FORMAT"

case "$FORMAT" in
"tape") echo "no random access!";; 
"cd") echo "old school";;
"mp3") echo "how modern";;
"lp") echo "total retro";;
esac
