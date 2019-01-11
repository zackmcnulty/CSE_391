PS3="What is your favorite food? " # Goes with the select stmt
echo "Welcome to the select example!"
echo "It prints out a list of choices"
echo "but does nothing interesting with the answer."
select CHOICE in "pizza" "sushi" "oatmeal" "broccoli"; do
	echo "You picked $CHOICE"
	break
done

