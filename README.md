# TP-POO-MIni-interpreteur
This a code source for a mini interpreter that work with two instructions (let/print) ,with a nice interface made using javaFx and CSS.

this is the BNF for the interpreter:
        <command line> ::="print" <expression> |"let" <variable> "=" <expression>
        <expression>::=[ "-" ] <term> [ [ "+" | "-" ] <term> ]...
        <terme>::=<facteur> [ [ "*" | "/" ] <facteur> ]...
        <facteur>::=<element> [ "^" <element> ]...
        <element>::=<nombre> | <variable> | "(" <expression> ")"| <nom-fonction-standard> "(" <expression> ")"
        <variable>::=<lettre> { <lettre> | <digit> }
        <nombre>::=<digit> {<digit>}

