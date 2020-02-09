# WAES RECRUITMENT CHALLENGE

[![CircleCI](https://circleci.com/gh/victorwardi/WaesScalableWeb.svg?style=svg)](https://circleci.com/gh/victorwardi/WaesScalableWeb)

The main goal of this project is to show my coding skills as a Java Software Developer.

## The assignment

- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
	- /v1/diff/{id}/left 
	- /v1/diff/{id}/right
	
- The provided data needs to be diff-ed and the results shall be available on a third end point
	- /v1/diff/{id}
	
- The results shall provide the following info in JSON format
	- If equal return that
	- If not of equal size just return that
	- If of same size provide insight in where the diffs are, actual diffs are not needed (So mainly offsets + length in the data)

## Online APP

https://waes-victor-api-assignmnet.herokuapp.com/