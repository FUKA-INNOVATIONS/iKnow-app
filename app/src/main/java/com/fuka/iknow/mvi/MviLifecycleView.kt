package com.fuka.iknow.mvi

import androidx.lifecycle.LifecycleOwner

interface MviLifecycleView<S : State, E : Event> : MviView<S, E>, LifecycleOwner
