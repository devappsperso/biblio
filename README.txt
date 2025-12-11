
Pour un nouveau projet, faire :

create a new repository on the command line :
=============================================
echo "# Projet biblio" >> README.txt
git init
git add README.txt
git commit -m "first commit"
  ## git branch -M main  ## pas nécessaire car par défaut il y a la branche master qu'on va utiliser
  ## git push -u origin main ## donc pas nécessaire aussi
git remote add origin https://github.com/devappsperso/biblio.git
git push -u origin master

ou push an existing repository from the command line
git remote add origin https://github.com/devappsperso/biblio.git
  ## git branch -M main   ## git push -u origin main
git push -u origin master