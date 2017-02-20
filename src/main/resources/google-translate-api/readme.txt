Google Translate API v2 Java
----------------------------

An unofficial Java wrapper for Google Translate API v2 (http://code.google.com/apis/language/translate/v2/getting_started.html).
For feature requests / bug reports / updates, please visit us at http://code.google.com/p/google-translate-api-v2-java.

Files
------------------
* google-translate-api-v2-java-core-XXX.jar - Contains full comprehensive access to all the API functionality.
  Depends on all the JAR files contained in lib-core

* google-translate-api-v2-java-cli-XXX.jar - Contains full comprehensive access to all the API functionality using a Command Line Interface.
  Depends on google-translate-api-v2-java-core-XXX.jar and all the JAR files contained in lib-core and lib-cli.



What's new?

Version 0.52
------------
* Internally splitting sourceTexts to avoid "Too many text segments" Google Translate API error.
  The splitting and sending is done internally in the translate and detect methods so there is no limitation of the length of sourceTexts as far as the user is concerned.
* Adding "location" and "locationType" to ApiError.ErrorEntry

Version 0.51
------------
* Changing default toString behaviour in the core module to allow easier usage in applications.
    Translation.toString() returns the translatedText
    Detection.toString() returns the language
    Language.toString() returns the name or the language if the name is null.
  The plain output format of the CLI module was not changed.
  The toString format can be determined by the "org.google.translate.api.v2.core.model.toString" Java property described below.
* Adding the "org.google.translate.api.v2.core.model.toString" Java property.
  Allowed values are:
    short - returns the most simple and important info (Default when using the core module).
    long - returns all the not null fields.
    full - returns all the fields (Default when using the CLI module).
  A constant with the property name can be found at org.google.translate.api.v2.core.model.AbstractResponseObject.TO_STRING_FORMAT.
* Adding ability to pass the Google API key to the CLI module using an environment variable "GOOGLE_API_KEY" instead of the apiKey option.
  If both are available, the apiKey option will be used.
  A constant with the env var name can be found at org.google.translate.api.v2.cli.TranslatorCli.GOOGLE_API_KEY.
* Adding the verbose option to allow more detailed info to assist troubleshooting.

Version 0.5
------------
* translate	- Translates source text(s) from source language to target language.
* languages	- List the source and target languages supported by the translate methods.
* detect    - Detect language of source text(s).