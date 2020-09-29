# spring-mvc-alex
modulo do curso Spring MVC Java Avancado de  Alex do Hotmart.

Nesta aplicação há um erro quando clicar na opção editar pessoa o sistema trava. Se alguém puder testar, seria uma boa contribuição ajudar a resolver este bug.

quando clico em editar ele aparece a mensagem na tela: An error happened during template parsing (template: "class path resource [templates/cadastro/cadastropessoa.html]").
No console aparece a mensagem:  An error happened during template parsing (template: "class path resource [templates/cadastro/cadastropessoa.html]")] with root cause "Property or field 'content' cannot be found on null". Esse content é referente ao trecho de código na página cadastropessoa.html: 
------------------------------------------------------------------------------------------------------------------------
<tr th:each="pessoa : ${pessoas.content}">
			<td th:text="${pessoa.id}"></td>
			<td><a th:href="@{/telefones/{idpessoa}(idpessoa=${pessoa.id})}"><span
					th:text="${pessoa.nome}"></span></a></td>
			<td th:text="${pessoa.sobrenome}"></td>
			<td><a th:if="${pessoa.curriculo != null}"
				th:href="@{/baixarcurriculo/{idpessoa}(idpessoa=${pessoa.id})}">Download</a>
				<a th:if="${pessoa.curriculo == null}" style="color: red;">Não
					existe</a></td>
			<td><a
				th:href="@{/editarpessoa/{idpessoa}(idpessoa=${pessoa.id})}">Editar</a>
			</td>
			<td><a
				th:href="@{/removerpessoa/{idpessoa}(idpessoa=${pessoa.id})}">Excluir</a>
			</td>
		</tr>
-----------------------------------------------------------------------------------------------------------------
Deu para ver que é problema com valor nulo.
