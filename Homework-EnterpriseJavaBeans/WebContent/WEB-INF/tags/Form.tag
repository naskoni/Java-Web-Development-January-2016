<%@ attribute name="username"%>
<%@ attribute name="currency" %>

<div class="container">
	<form class="form-signin" method="post" action="/Homework-EnterpriseJavaBeans/BankOperationController">
    	<h2 class="form-signin-heading">Enjoy our banking!</h2>
      	<label for="inputEmail" class="sr-only">Username</label>
      	<input type="text" name="username" id="inputEmail" class="form-control" placeholder="${username}"
      	value="${username}" required autofocus>
     	<label for="inputEmail" class="sr-only">Sum</label>
      	<input type="number" name="amount" id="inputEmail" class="form-control" placeholder="Enter amount to operate" required>
      	<br>
		<p><strong>Choose operation:</strong></p>
			<p>
				<input type="radio" name="operation" value="Deposit" required><strong>Deposit</strong><br />
				<input type="radio" name="operation" value="Withdraw"><strong>Withdraw</strong><br />	
			</p>
			<select name="currency">
  					<option value="BGN">BGN</option>
  					<option value="USD">USD</option>
  					<option value="EUR">EUR</option>  
			 </select> 
      	<button type="submit" name="login" value="1" class="btn btn-lg btn-primary btn-block">Bank!</button>        
    </form>
    <jsp:doBody/>
</div> <!-- /container -->