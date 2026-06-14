package compilador;
import java_cup.runtime.Symbol;

%%
%class Lexer
%type java_cup.runtime.Symbol
%cup
%unicode
%line
%column

Letra = [a-zA-Z]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*
Numero = {Digito}+("."{Digito}+)?
Cadena = \"[^\"]*\"
Espacio = [ \t\r\n]+

%%
/* Palabras Reservadas y Tipos */
"clase"    { return new Symbol(sym.CLASE, yyline + 1, yycolumn + 1, yytext()); }
"texto"    { return new Symbol(sym.TEXTO, yyline + 1, yycolumn + 1, yytext()); }
"decimal"  { return new Symbol(sym.DECIMAL, yyline + 1, yycolumn + 1, yytext()); }
"metodo"   { return new Symbol(sym.METODO, yyline + 1, yycolumn + 1, yytext()); }
"nuevo"    { return new Symbol(sym.NUEVO, yyline + 1, yycolumn + 1, yytext()); }
"imprimir" { return new Symbol(sym.IMPRIMIR, yyline + 1, yycolumn + 1, yytext()); }

/* Símbolos Especiales */
"{"        { return new Symbol(sym.LLAVE_A, yyline + 1, yycolumn + 1, yytext()); }
"}"        { return new Symbol(sym.LLAVE_C, yyline + 1, yycolumn + 1, yytext()); }
"("        { return new Symbol(sym.PAREN_A, yyline + 1, yycolumn + 1, yytext()); }
")"        { return new Symbol(sym.PAREN_C, yyline + 1, yycolumn + 1, yytext()); }
"="        { return new Symbol(sym.IGUAL, yyline + 1, yycolumn + 1, yytext()); }
";"        { return new Symbol(sym.PYC, yyline + 1, yycolumn + 1, yytext()); }
"."        { return new Symbol(sym.PUNTO, yyline + 1, yycolumn + 1, yytext()); }
","        { return new Symbol(sym.COMA, yyline + 1, yycolumn + 1, yytext()); }

/* Dinámicos */
{Identificador} { return new Symbol(sym.ID, yyline + 1, yycolumn + 1, yytext()); }
{Numero}        { return new Symbol(sym.NUMERO, yyline + 1, yycolumn + 1, yytext()); }
{Cadena}        { return new Symbol(sym.CADENA, yyline + 1, yycolumn + 1, yytext()); }

/* Ignorar espacios */
{Espacio}       { /* ignorar */ }

/* Manejo de Errores Léxicos */
. { 
    System.err.println("Error léxico: Carácter no reconocido '" + yytext() + "' en línea " + (yyline+1) + ", columna " + (yycolumn+1)); 
    return new Symbol(sym.error, yyline + 1, yycolumn + 1, yytext()); 
}