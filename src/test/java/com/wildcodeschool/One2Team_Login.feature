Feature: One2Team_Login

  Scenario: Login success
    #Before
    Given user navigates to "https://chewie.one2team.com/"
    When user enters "userName" "etudiant10"
    When user enters "passWord" "Etudiant10*"
    When user enters "domainName" "telco"
    When user clicks on Connect button
    Then user is redirected to One2Team homepage
    #After


  Scenario Template:
    #
    #
    #
    Examples:


#les message d'erreur
    #username vide --> Username field is empty.
    #mdp vide --> Wrong combination of username/password and domain.
    #domaine vide --> Wrong combination of username/password and domain.
    #username ou mdp ou domaine erroné --> Wrong combination of username/password and domain.