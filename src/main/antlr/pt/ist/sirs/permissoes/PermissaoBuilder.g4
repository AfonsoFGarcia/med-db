grammar PermissaoBuilder;

@header {
import java.util.ArrayList;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.logicas.*;
import java.io.*;
}

@members {
	public static Permissao getPermissao(String perm) {
		PrintStream stderr = System.err;
		System.setErr(new PrintStream(new OutputStream() {
                public void write(int b) {
                    //DO NOTHING
                }
            }));
		
		String sanitizedPerm = perm.toLowerCase().replaceAll(" ", "");
		PermissaoBuilderLexer lex = new PermissaoBuilderLexer(new ANTLRInputStream(sanitizedPerm));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		PermissaoBuilderParser parser = new PermissaoBuilderParser(tokens);
		
		Permissao permissao = null;
		
		try {
			permissao = parser.b().value;
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		
		System.setErr(stderr);
		
		return permissao;
	}
}

/* PARSER RULES */

b returns [Permissao value]
 :	OR BP s CP		{$b.value = new PermissaoOuLogico(null, $s.value);}
 |	AND BP s CP		{$b.value = new PermissaoELogico(null, $s.value);}
 |	NOT BP b_1 CP	{$b.value = new PermissaoNaoLogico(null, $b_1.value);}
 |	MDESP			{$b.value = new PermissaoMedicoDaEspecialidade(null);}
 |	MDP				{$b.value = new PermissaoMedicoDaPessoa(null);}
 |	MDU				{$b.value = new PermissaoMedicoDeUrgencia(null);}
 |	MDEST			{$b.value = new PermissaoMedicoDoEstabelecimento(null);}
 |	MDR				{$b.value = new PermissaoMedicoDoRegisto(null);}
 |	PDR				{$b.value = new PermissaoPacienteDoRegisto(null);}
 |	PPDE			{$b.value = new PermissaoPoliticaDeEspecialidade(null);}
 |	PP				{$b.value = new PermissaoPublica(null);}
 ;

b_1 returns [Permissao value]
 :	b				{$b_1.value = $b.value;}
 ;
 
s_1 returns [ArrayList<Permissao> value]
 :	s		{$s_1.value = $s.value;}
 ;

s returns [ArrayList<Permissao> value]
@init {
	$value = new ArrayList<Permissao>();
}
 :	b VR s_1		{$s.value.add($b.value); $s.value.addAll($s_1.value);}
 |	b				{$s.value.add($b.value);}
 ;

/* LEXER RULES */

 OR
 :	'or'
 ;
 AND
 :	'and'
 ;
 NOT
 :	'not'
 ;
 MDESP
 : 	'mdesp'
 ;
 MDP
 :	'mdp'
 ;
 MDU
 :	'mdu'
 ;
 MDEST
 :	'mdest'
 ;
 MDR
 :	'mdr'
 ;
 PDR
 :	'pdr'
 ;
 PPDE
 :	'ppde'
 ;
 PP
 :	'pp'
 ;
 BP
 :	'('
 ;
 CP
 :	')'
 ;
 VR
 :	','
 ;
 INT
 :	(DIGIT)+
 ; 
 DIGIT
 :	'0'..'9'
 ;