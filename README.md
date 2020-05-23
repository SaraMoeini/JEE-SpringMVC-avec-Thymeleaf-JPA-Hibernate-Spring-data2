# JEE-SpringMVC-avec-Thymeleaf-JPA-Hibernate-Spring-data2
Banque

## 1 : les entites

## 2: http://localhost/phpmyadmin/ for create bdd MyBanque

## 3: mapping objet relationnelle  => jpa / springdata

* Gere les entite soit dirctement avec JPA et entityManager
* soit avec springData(JpaRepository)

## 4: la couche Metier/ Service => les traitement
* consulter compte
* consulter les operations d'un  compte
* effectuer un versement
* effectuer un virement
* effectuer un retrait

** on peut tester avant passer a la couche web

## 5: la couche web: controller
et vue:

fichier template de thymeleaf comme :
<!DOCTYPE html>
<html xmlns:th="http://www.thymeLeaf.org"
xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="UTF-8">
<title>Ma Banque</title>
  <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css"
	th:href="@{/css/bootstrap.min.css}"/> 
	<link rel="stylesheet" type="text/css" href="../static/css/style.css"
	th:href="@{/css/style.css}"/> 
</head>
<body>
  <header>
    <div>
	  <div>
	    <ul>
	      <li><a></a></li>
	      <li><a></a></li>
	      <li><a></a></li>
	    </ul>
	  </div>    
    </div>
  </header>
	<section></section>
	<footer></footer>
</body>
</html>

* => pagable

## 6: spring Security :
* ajouter la dependance dans POM

* definir les roles  en memoire et formLogin() basic de springSecurity
