## CI Workflow

1. Checking out
..*Check out working copy from mainline 
	git pull
	git checkout -b "issue/X"

2. Modify and create local build
..*Alter code / add or change automated tests
..*Perform automated build on local machine

3. Committing to the repository
..*Update working copy
	git checkout master
	git pull origin
	get checkout <branch name>
	git merge master

..* Rebuild on local machine
...*Solve merge conflicts 
...*Rebuild and repeat until working copy is stable
	git commit (fix #X) "good message"
	git push origin issue/X
..*Commit changes to mainline
	Create pull request 
	Solve eventual comments

4. Build on integration machine
..* Ensure build succeeds
