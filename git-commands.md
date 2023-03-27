#!/bin/bash

## Git commands to iteratively add in the SnagIt Images and the Source Code

```sh
rm -rf src target
ls
cp -r ~/eclipse-workspace/spring-mvc-demo/* .
ls 
cd screenshots
cp -r ~/raghs/official/training/SketchWow-Images-Docs/27Mar2023-Mon-SnagIt-Images .
cd ..
git status
git add .
git status
git commit -m "27Mar2023-Mon-Session01-SpringJDBC - DataSource config - XML, Properties, SnagIt Images Added"
git push
```
