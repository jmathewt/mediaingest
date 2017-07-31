# Instructions to checkout the project

1. Git clone the repo
2. In Eclipse/STS import this as a maven project
3. Choose project -> Run As -> Run Configurations -> Environment
4. Add values for `datasource_url`, `datasource_username`, `datasource_password`, `base_path_to_repo` (the source of media files), `destination_path` (where the media files should be saved), `email_password` (email account password from where the report has to be sent), `email_username` (email account address from where the report has to be sent)
5. Run as a spring boot app (in STS)

# Endpoints
1. `http://<host>/mediaDrop/bag/bag_name` - to get the assets of a bag

2. `http://<host>/mediaDrop/getMedia?names=media1.mov,media2.mp4&email=emailId@something.org` - will copy assets from repo to default destination

## TODO:
1. The destination to copy files should be made a query parameter
2. Add logging instead of printing to console
3. Add unit tests

# Usage:
1. Find the bag from which media files need to be copied.
2. Use the `/mediaDrop/bag/bag_name` endpoint to ensure that the bag exists in repo and the media files you are expecting to copy is part of that bag. If the name of the bag is wrong, it will return a 404 error saying that the bag could not be found.
3. Once you find out that the media files exist, copy the name exactly as is shown in the response.
4. Use the endpoint `/mediaDrop/getMedia?names=media1.mov,media2.mp4&email=emailId@something.org` replace the values of names with the media names that you copied. If you have more you can just keep adding them separated by commas. Also provide the email address to which the app has to send the results of the job. Once the request is sent, you will be given a request id that looks like `76130BD2-28C9-4601-874F-2F2881D8A45B`.
5. As soon as the app gets the request it starts copying files to the destination in the background. After copying files, it computes the checksum to ensure that the file is intact.
6. Once the copying is complete, you will receive an email from mediaingestreporter with the list of successful files and unsuccessful files. If the file already exist in the destination, the file will not be copied. The email will not have any results.
7. The successful files are the ones that copied successfully to the destination and the checksums match with the source. The unsuccessful files are the ones whose copy operation failed for some reason or after a successful copy, the checksums do not match.  
