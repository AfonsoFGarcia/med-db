grammar PermissaoBuilder;

@header {
	import java.util.ArrayList;
	import pt.ist.sirs.permissoes.Permissao;
}

tokens{
	OR		"or"
	AND		"and"
	NOT		"not"
	MDESP 	"mdesp"
	MDP		"mdp"
	MDU		"mdu"
	MDEST	"mdest"
	MDR		"mdr"
	PDR		"pdr"
	PPDE	"ppde"
	PP		"pp"
	BP		'('
	CP		')'
	VR		','
}

@members {
	public static void main(String[] args) throws Exception {
		PermissaoBuilderLexer lex = new PermissaoBuilderLexer(new ANTLRFileStream(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(lex);

		PermissaoBuilderParser parser = new PermissaoBuilderParser(tokens);

		try {
			parser.b();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
}

/* PARSER RULES */

b returns [Permissao value]
 :	s 				{$b.value = $s.value.get(0);}
 ;

s returns [ArrayList<Permissao> value]
@init {
	$value = new ArrayList<Permissao>();
}
 :	OR BP s_1 CP	{$s.value.add(new PermissaoOuLogico($s_1.value))}
 |	AND BP s_1 CP {$s.value.add(new PermissaoOuLogico($s_1.value))}
 |	c				{$s.value = $c.value;}
 ;

s_1 returns [ArrayList<Permissao> value]
 :	s 				{$s_1.value = $s.value;}
 ;

s_2 returns [ArrayList<Permissao> value]
 :	s 				{$s_2.value = $s.value;}
 ;

c returns [ArrayList<Permissao> value]
@init {
	$value = new ArrayList<Permissao>();
}
 :	s_1 VR s_2		{$c.value.addAll($s_1.value); $c.value.addAll($s_2.value);}
 |	NOT BP t CP		{$c.value.add($t.value);}
 |	t
 ;

t returns [Permissao value]
 :	
 |	MDESP			{$t.value = new PermissaoMedicoDaEspecialidade(null);}
 |	MDP				{$t.value = new PermissaoMedicoDaPessoa(null);}
 |	MDU				{$t.value = new PermissaoMedicoDeUrgencia(null);}
 |	MDEST			{$t.value = new PermissaoMedicoDoEstabelecimento(null);}
 |	MDR				{$t.value = new PermissaoMedicoDoRegisto(null);}
 |	PDR				{$t.value = new PermissaoPacienteDoRegisto(null);}
 |	PPDE			{$t.value = new PermissaoPoliticaDeEspecialidade(null);}
 |	PP				{$t.value = new PermissaoPublica(null);}
 ;

/* LEXER RULES */

 INT
 :	(DIGIT)+
 ;
 
 fragment DIGIT
 :	'0'...'9'
 ;