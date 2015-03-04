# UnikornTube

##Convention de nommage:

Variable d'instance =>   _variable

Paramètre de fonction => parVariable

Variable locale => locVariable

###Example:

public class Exemple {
		//	Exemple de variable d'instance :: préfixe '_'
		private int _maVariable;

		/**
		*	Gets something
		*/
		public int GetSomething () {
			return _maVariable;
		}

		/**
		*	Sets Something : Exemple de paramètre :: préfixe 'par'
		*/
		public HRESULT SetSomething ( int parVariable ) {
			if ( 0 <= parVariable ) {
				int locExemple = 0;							//	Exemple de variable locale :: préfixe loc
				_maVariable = parVariable;
			}
		}
}