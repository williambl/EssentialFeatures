# Contributing to EssentialFeatures
Thanks for wanting to contribute to EssentialFeatures! Pull requests and
issues are always welcome.

## Things you should know
 - I personally use intelliJ IDEA - you'll probably have best results using
it too.
 - You should make sure that your code is readable, and that more complex
code is commented (I'm not too good at this myself, so any pull requests
improving readability would be greatly appreciated)
 - It would be appreciated if you provided textures and localisations for
any blocks, but I'm fine if you don't.
 - Please always create one commit per milestone (e.g one when creating a
block, one when making the tileentity for the block, one for making the
texture for the block, etc.)
 - It would be great if you explained your changes in the pull request.

## Setting up
 - After cloning the repo, run `./gradlew setupDecompWorkspace`.
 - If you use Eclipse, you will then need to run `./gradlew eclipse`.
 - If you use IntelliJ IDEA, then you should run `./gradlew genIntellijRuns`.
 Then import build.gradle into IDEA.
 - Your IDE should now work with the repo!
