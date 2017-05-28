package com.stt.project;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeechConverter {

	// Some available voices are (kevin, kevin16, alan)
	private static final String VOICE_NAME_KEVIN = "kevin16";
	private static Voice voice;
	
	public TextToSpeechConverter() {

		VoiceManager vm = VoiceManager.getInstance();		
		voice = vm.getVoice(VOICE_NAME_KEVIN);
		voice.allocate();
	}

	public static void speak(String inputText) {

		if(inputText != null && !inputText.isEmpty()) {
			
			voice.speak(inputText);
		}
		else{
			voice.speak("aksdhkasjhd");
		}
	}
	public static void main(String[] args) {
		speak("this");
	}

}