package com.mega.megatuner.Interfaces;

import com.mega.megatuner.Interfaces.TuneRenderer;

public interface TuneView {

	void invalidateRender();
	TuneRenderer getRenderer();
    void Cleanup();
}
