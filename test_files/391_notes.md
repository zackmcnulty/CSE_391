## CSE 391 NOTES!


### Lect 1
	* clear: cleans terminal window

### Lect 2: Basic bash Operations
	* operation > file overwrites file with output of operation while >> appends
	* sort: can sort whatever is given to it (sort file > output.txt sorts by line in file)
	* command < source: uses source as input stream rather than console (similar to var << std::cin from C++)
	* piping (|) : send output of command 1 to use as input stream for command 2 (command1 | command2)
	* command1 && command2: only run command2 if command1 succeeds
	* command1 ; command2: run command2 regardless of whether or not command1 succeeds
	* curl vs wget
		- curl outputs to console what it reads at the address while wget downloads and saves file to computer
	* read: reads input from console (user input)

### Lect 3: SSH
	* command1 $(command2) passes the results of command2 as an argument of command1; different from piping | 
		which simply changes the source of the input stream (NOT an explicit parameter sometimes)
		only works well if command2 has a short output (like pwd or which BLANK)	
	* xargs: for each line in input, run the following command with that line as an argument
		find ~ -name "*.class" | xargs rm      removes all .class files
	* processes are a list of commands being run through your terminal
		- ps lists all processes being run by given user
		- top lists most memory/CPU costly processes
		- kill (PID) terminates a process by PID
		- killall (name) terminates process(es) by name
	* &: append to end of command to run in background (terminal still free to run other stuff)
	* scp: used for securely transferring files between two locations (i.e. from local computer to server)

### Lect 4: Permissions
	* groups are collections of users. Can give permission to do certain operations to entire group
		rather than each user individually. 
		- see what groups you are in using : groups
		- list of group membership stored in /etc/group
		- use ls -l to see what group is associated to each file (4th column)
	* chgrp = change group associated to the file
	* chmod: changes permissions of a given file
		- use ls -l to see files and their permissions
		- r = read, w = write, x = execute
			drwxrw-r--
			^ user has full access, group cannot execute, others can only read
			first letter for directory?
			next 3 are permissions of user
			next 3 are permissions of group associated to file
			next 3 are permissions to anyone else
				* a - means that action is not permitted
		- u = user, g = group, o = other
		ex) chmod g + x FILENAME gives the group execute permissions to FILENAME
			chmod u - w FILENAME revokes writing permissions to the user

### Lect 5: Basic Bash Scripts
	* shebang : first line of bash file (#!/bin/bash) --> set the interpreter (i.e. which version of bash to use)
	* NOTE: make sure script is executable! (chmod u+x myscript.sh)
	* comments with #
	* variables
		- x=6   NO SPACES!
		- reference a variable with $:   echo $var_name
		- variable=$(command)
	* arithmetic --> must be strings!
		- let z = "$x + $y"
	* $PATH system variable: where to look for executables (i.e. when I call python script.py, where should I look
		for python binaries? $PATH is a list of paths to check for python)
		- by adding paths to $PATH, we cna execute executables from anywhere!
			- PATH=$PATH:$(pwd)    or replace $(pwd) with any path you want	
		- example: try one_ring.sh 
	* command line arguments (for bash scripts)
		- $0 is the name of the command
		- $1, $2, ... are arguments 1,2,..
		- $# is number of arguments
		- $@ = array of arguments
	* for loops!
		- for var_name in value1 value2 value 3; do
			commands
		  done

		can pass in array to for loop (i.e. for name in $@; do ...) or the output of some other command 
		helpful tip $(seq 4) provides the basic "run 4 times; i = 1,2,3,4" behavior
			- seq start step stop

### Lect 6: Advanced Bash Scripts

	* exit statuses ->
		- tell you whether the shell script completed successfully
		- 0 = success; any other # = failure
		- can check status of last command with $?
			- echo $?
	* if statements
		- if [ condition ]; then
			commands
		  fi

		- if [ condition ]; then
			commands1
		  elif [ condition ]; then
		  	commands2
		  else
		  	commands3
		  fi
		*** [ SPACE condition SPACE ] spaces and [] required! 
	* test:
		- test condition, and return true (exit code 0) or false (exit code 1)
		- string variable comparisons: == != \< \>
		- numeric comparisons: -lt (<), -le (<=), -gt (>), -ge (>=), -eq (==), -ne (!=) 
		- -e, -f, -d: tests whether path/file/directory exists
		- -r -w -x: tests whether file exists and is readable/writable/executable

		- AND : [ cond1 ] && [ cond2 ];
		- OR: [ cond1 ] || [ cond2 ];
		- NOT: [ ! cond1 ];
	* while loops (continue while a condition is true)
		while [ cond ]; do
			commands
		done

	* until loops (continue until a condition becomes true)
		until [ cond  ]; do
			commands
		done
	* select statement -> gives user a list of choices they can refer to with a number
		PS3 = "prompt" //special variable; NEEDS to be named this
		select choice in choice1 choice2 choice3; do
			commands with $choice
			break (might want to check if input is valid)
		done
	* case statement
		- kind of like a series of if statements
		case "$var" in
			"tape") echo "tapes are cool;
			...
		esac
	* ARRAYS
		initializae:                        array = (element1 element2 ... elementN)
		set value:                          array[index] = value 
		get value:     			    ${array[index]} 
		all elements(separated by spaces):  ${array[*]}
		array length:			    ${#array[*]}
	* functions
		function name() {
			commands $1 $2 ... 
			// $1 is first param, $0 is func name, $2 is second param
		}
	* tar (NOT compression; just conveniently collects stuff)
		collects group of files together
		-cf filename.tar files_to_archive(list) creates a tar file
		-xf filename.tar extracts files
	* zip/unzip; gzip/gunzip
		- gzip filename compresses file into .gzip file
		- gunzip filename.gz uncompresses file

	*** Sometimes file is BOTH .tar and .gz, so to both unzip and extract tar files
		tar -xzf filename.tar.gz


### Lect 7
	* egrep
		- extended grep
		- searches through file for given regular expression and returns lines
			that have match for pattern

			. = single letter wildcard
			^ = start of line
			$ = end of line
			\< = pattern is at beginning of word
			\> = pattern at end of word
				i.e. "\<for\>" matches lines with word "for"
			pat1 | pat2 = match pattern1 OR pattern2
			() = used for grouping
				- (Homer | Marge) Simpson matches Homer Simpson or Marge Simpson
			use \ to escape special characters

			* matches 0 or more occurences of the preceeding group
				- a(bc) matches a, abc, abcbc, abcbcbc
			+ matches 1 or more occurence of preceeding group
			? matches 0 or 1 occurence of preceeding group
			{min, max} occurences of preceeding group
			{min, } at least min of preceeding group
			{, max} up to max occurences of preceeding group
			{num} exactly num occurences of preceeding group

			character ranges []
				most characters act normally, even modifiers like ?
				ranges: use - to match range of characters
					[0-9] [a-zA-Z] = numbers 0 thru 9, etc
				^ at front of character set negates it
					[^0-9] = NOT # 0-9


### Lect 8: Large Program Management Make and Ant

